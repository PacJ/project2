package com.example.recommend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.list.dto.ListDTO;
import com.example.recommend.dao.RecommendDAO;

@Service
public class RecommendServiceImp implements RecommendService {
	@Autowired
	private RecommendDAO recommendDAO;
	
	
	@Override
	public List<ListDTO> recentmoviesProcess(String member_id) {
		// TODO Auto-generated method stub
		return recommendDAO.recentmovies(member_id);
	}
	@Override
	public ListDTO getmovieProcess(String movie_id) {
		// TODO Auto-generated method stub
		return recommendDAO.getmovie(movie_id);
	}
	

}
