package com.example.list.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.actor.dto.ActorDTO;
import com.example.director.dto.DirectorDTO;
import com.example.list.dao.ListDAO;
import com.example.list.dto.ContentsDTO;
import com.example.list.dto.ListDTO;
import com.example.review.dto.CommentsDTO;
import com.example.review.dto.RatingDTO;
import com.example.review.dto.ReviewDTO;

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
	
	
	// 검색 영역
	@Override
	public List<ListDTO> searchMovies(String query) {
		return listDAO.searchMovies(query);
	}

	@Override
	public List<ActorDTO> searchActors(String query) {
		return listDAO.searchActors(query);
	}

	@Override
	public List<DirectorDTO> searchDirectors(String query) {
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

	@Override
	public ActorDTO selectByActorIdProcess(String actor_id) {
		return listDAO.selectByActorId(actor_id);
	}

	@Override
	public DirectorDTO selectByDirIdProcess(String dir_id) {
		return listDAO.selectByDirId(dir_id);
	}

	@Override
	public List<String> selectAllProcess() {
		return listDAO.selectAll();
	}
	

	// 영화 상세 컨텐츠
	@Override
	public ContentsDTO getContentsProcess(int movie_id) {
		ContentsDTO contents = listDAO.getMovieById(movie_id);
		contents.setActorDTO(listDAO.getActorsByMovieId(movie_id));
		contents.setDirectorDTO(listDAO.getDirectorsByMovieId(movie_id));
		contents.setGenreDTO(listDAO.getGenresByMovieId(movie_id));
		contents.setImagesDTO(listDAO.getImagesByMovieId(movie_id));
		contents.setReviewDTO(listDAO.getReviewsByMovieId(movie_id));
		return contents;
	}

	
	// 영화 코멘트
	@Override
	public ReviewDTO findReviewByIdProcess(int movie_id, int member_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("movie_id", movie_id);
		map.put("member_id", member_id);
		
		return listDAO.findReviewById(map);
	}

	@Override
	public void postCommentProcess(CommentsDTO comment) {
		listDAO.postComment(comment);
	}

	@Override
	public void updateCommentProcess(CommentsDTO comment) {
		listDAO.updateComment(comment);
	}

	@Override
	public void postRatingProcess(RatingDTO rating) {
		listDAO.postRating(rating);
	}

	@Override
	public void updateRatingProcess(RatingDTO rating) {
		listDAO.updateRating(rating);
	}

	@Override
	public void deleteCommentProcess(int movie_id, int member_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("movie_id", movie_id);
		map.put("member_id", member_id);
		
		listDAO.deleteComment(map);
	}



}
