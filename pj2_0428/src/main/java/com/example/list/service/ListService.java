package com.example.list.service;

import java.util.List;
import java.util.Map;

import com.example.actor.dto.ActorDTO;
import com.example.director.dto.DirectorDTO;
import com.example.list.dto.ContentsDTO;
import com.example.list.dto.ListDTO;
import com.example.review.dto.CommentsDTO;
import com.example.review.dto.RatingDTO;
import com.example.review.dto.ReviewDTO;

public interface ListService {
	public List<ListDTO> getTopRatedMoviesProcess();
	public List<ListDTO> getTopRatedClassicProcess();
	
	// 검색 영역
	public List<ListDTO> searchMovies(String query);
	public List<ActorDTO> searchActors(String query);
	public List<DirectorDTO> searchDirectors(String query);
	
	//검색 자동완성
	public List<String> selectAllProcess();
	
	// 배우/감독 정보(사진, 이름)
	public ActorDTO selectByActorIdProcess(String actor_id);
	public DirectorDTO selectByDirIdProcess(String dir_id);
	
	//출연 영화
	public List<ListDTO> castMoviesProcess(String actor_id);
	
	//감독 영화
	public List<ListDTO> dirMoviesProcess(String dir_id);
	
	// 영화 상세 페이지
	public ContentsDTO getContentsProcess(int movie_id);
	
	// review > comment, rating
	public void postCommentProcess(CommentsDTO comment);
	public void updateCommentProcess(CommentsDTO comment);
	public void deleteCommentProcess(int movie_id, int member_id);
	public void postRatingProcess(RatingDTO rating);
	public void updateRatingProcess(RatingDTO rating);
	
	public ReviewDTO findReviewByIdProcess(int movie_id, int member_id);
	
}
