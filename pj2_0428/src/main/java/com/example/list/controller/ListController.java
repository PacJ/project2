package com.example.list.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.list.dto.ContentsDTO;
import com.example.list.service.ListService;
import com.example.review.dto.CommentsDTO;
import com.example.review.dto.RatingDTO;
import com.example.review.dto.ReviewDTO;

@CrossOrigin({"http://localhost:3000"})
@RestController
public class ListController {
	
	@Autowired
	private ListService listService;
	
	public ListController() {
		
	}
	
	
	// 메인 리스트
	// http://localhost:8090/
	@GetMapping("/")
	public Map<String, Object> listExecute() {
		Map<String, Object> map = new HashMap<>();
		map.put("topRatedList", listService.getTopRatedMoviesProcess());
		map.put("topRatedClassic", listService.getTopRatedClassicProcess());
		
		return map;
	}
	
	
	
	// 영화 상세정보
	@GetMapping("/contents/{movie_id}")
	public ContentsDTO contentsExecute(@PathVariable("movie_id") int movie_id) {
		return listService.getContentsProcess(movie_id);
	}
	
	
	
	// 코멘트, 별점 작성
	@PostMapping("/comment")
	public void postCommentExecute(@RequestBody CommentsDTO comment) {
		listService.postCommentProcess(comment);
	}
	
	@PutMapping("/comment")
	public void updateCommentExecute(@RequestBody CommentsDTO comment) {
		listService.updateCommentProcess(comment);
	}
	
	@DeleteMapping("/comment/{movie_id}/{member_id}")
	public void deleteCommentExecute(@PathVariable("movie_id") int movie_id, @PathVariable("member_id") int member_id) {
		listService.deleteCommentProcess(movie_id, member_id);
	}
	
	@PostMapping("/rating")
	public void postRatingExecute(@RequestBody RatingDTO rating) {
		listService.postRatingProcess(rating);
	}
	
	@PutMapping("/rating")
	public void updateRatingExecute(@RequestBody RatingDTO rating) {
		listService.updateRatingProcess(rating);
	}
	
	@GetMapping("/comment/{movie_id}/{member_id}")
	public ReviewDTO getCommentExecute(@PathVariable("movie_id") int movie_id, @PathVariable("member_id") int member_id) {
		System.out.println("냐냐냐" + movie_id + member_id);
		return listService.findReviewByIdProcess(movie_id, member_id);
	}
	
	
	
	
	
	
	
	
	
	
}
