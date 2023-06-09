package com.example.list.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.actor.dto.ActorDTO;
import com.example.director.dto.DirectorDTO;
import com.example.list.dto.ListDTO;

@Mapper
@Repository
public interface ListDAO {
	public List<ListDTO> getTopRatedMovies();
	public List<ListDTO> getTopRatedClassic();
	public ListDTO getContents(int movie_id);
	
	//검색 영역
	public List<ListDTO> searchMovies(String query);
	public List<ActorDTO> searchActors(String query);
	public List<DirectorDTO> searchDirectors(String query);
	
	// 배우/감독 정보
	public ActorDTO selectByActorId(String actor_id);
	public DirectorDTO selectByDirId(String dir_id);
	
	//출연 영화
	public List<ListDTO> castMovies(String actor_id);
	
	//감독 영화
	public List<ListDTO> dirMovies(String dir_id);
	
	//전체선택(자동완성 기능)
	public List<String> selectAll();
}
