package com.example.list.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.actor.dto.ActorDTO;
import com.example.director.dto.DirectorDTO;
import com.example.list.dao.ListDAO;
import com.example.list.dto.ListDTO;

@Service
public class ListServiceImp implements ListService {
	
	@Autowired
	private ListDAO listDAO;
	
	@Override
	public List<ListDTO> getTopRatedMoviesProcess() {
		return listDAO.getTopRatedMovies();
	}

	@Override
	public List<ListDTO> getTopRatedClassicProcess() {
		return listDAO.getTopRatedClassic();
	}

	@Override
	public ListDTO getContentsProcess(int movie_id) {
		return listDAO.getContents(movie_id);
	}

	// 검색 영역
	@Override
	public List<ListDTO> searchMovies(String query) {
		// TODO Auto-generated method stub
		return listDAO.searchMovies(query);
	}

	@Override
	public List<ActorDTO> searchActors(String query) {
		// TODO Auto-generated method stub
		return listDAO.searchActors(query);
	}

	@Override
	public List<DirectorDTO> searchDirectors(String query) {
		// TODO Auto-generated method stub
		return listDAO.searchDirectors(query);
	}

	@Override
	public List<ListDTO> castMoviesProcess(String actor_id) {
		return listDAO.castMovies(actor_id);
	}

	@Override
	public List<ListDTO> dirMoviesProcess(String dir_id) {
		return listDAO.dirMovies(dir_id);
	}

}
