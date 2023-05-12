package com.example.list.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.list.dto.ContentsDTO;
import com.example.list.dto.RecommendDTO;
import com.example.list.service.ListService;
import com.example.review.dto.CommentsDTO;
import com.example.review.dto.LikesDTO;
import com.example.review.dto.RatingDTO;
import com.example.review.dto.ReviewDTO;
import com.example.review.dto.ReviewInfoDTO;
import com.example.wish.dto.WishDTO;

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
	
	
	
	//	member_id인 사람이 마지막으로 본 영화 기반 추천
	@GetMapping("/recommend/{member_id}")
	public List<RecommendDTO> recMovie(@PathVariable("member_id") int member_id) {

		// 마지막으로 후기 남긴 영화 DTO 가져오기
		ContentsDTO lastMovie = listService.selectLastSeenProcess(member_id);

		if (lastMovie != null) {
			int lastMovieId = lastMovie.getMovie_id();
			System.out.println(lastMovieId);

			// Flask url 설정
			String url = UriComponentsBuilder.fromUriString("http://localhost:5000/recommend")
					.queryParam("MOVIE_ID", lastMovieId) // 검색할 영화 아이디 값
					.toUriString();

			RestTemplate restTemplate = new RestTemplate();

			// Flask 결과 받아오기
			String result = restTemplate.getForObject(url, String.class);

			System.out.println(result);

			// 결과값 편집
			String[] recMovie = result.replaceAll("[^\\d,]", "").split(",");

			// 영화 아이디 배열에 결과 추가
			int[] recMovieId = new int[recMovie.length];
			for (int i = 0; i < recMovie.length; i++) {
				System.out.println(recMovie[i] + "공백제거함.");
				recMovieId[i] = Integer.parseInt(recMovie[i]);

			}
			// 추천 영화 DTO List에 담기(recMovieId에 담긴 영화 id로 선택)
			List<RecommendDTO> recList = new ArrayList<RecommendDTO>();
			for (int i = 0; i < recMovieId.length; i++) {
				RecommendDTO rec = listService.selectByIdProcess(recMovieId[i]);
				recList.add(rec);
				System.out.println(rec.getTitle());
			}
			return recList;
		}
		return null;
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
	
	@DeleteMapping("/rating/{movie_id}/{member_id}")
	public void deleteRatingExecute(@PathVariable("movie_id") int movie_id, @PathVariable("member_id") int member_id) {
		listService.deleteRatingProcess(movie_id, member_id);
	}
	
	@GetMapping("/comment/{movie_id}/{member_id}")
	public ReviewDTO getCommentExecute(@PathVariable("movie_id") int movie_id, @PathVariable("member_id") int member_id) {
		System.out.println("리뷰썼냐" + movie_id);
		return listService.findReviewByIdProcess(movie_id, member_id);
	}
		
	

	// 보고싶어요
	@PostMapping("/wish")
	public void postWishExecute(@RequestBody WishDTO wish) {
		listService.postWishProcess(wish);
	}
	
	@DeleteMapping("/wish/{movie_id}/{member_id}")
	public void deleteWishExecute(@PathVariable("movie_id") int movie_id, @PathVariable("member_id") int member_id) {
		listService.deleteWishProcess(movie_id, member_id);
	}
	
	@GetMapping("/wish/{movie_id}/{member_id}")
	public WishDTO getWishExecute(@PathVariable("movie_id") int movie_id, @PathVariable("member_id") int member_id) {
		System.out.println("보고싶어요" + movie_id);
		return listService.findWishByIdProcess(movie_id, member_id);
	}
	
	
	
	// 인생영화
	@GetMapping("/favorite/{member_id}")
	public ContentsDTO findFavoriteByIdExecute(@PathVariable("member_id") int member_id) {
		System.out.println(listService.findFavoriteByIdProcess(member_id));
		return listService.findFavoriteByIdProcess(member_id);
	}
	
	// 인생영화 취소 > 0
	@PutMapping("/favorite/{member_id}")
	public void deleteFavoriteExecute(@PathVariable("member_id") int member_id) {
		listService.deleteFavorite(member_id);
	}
	
	// 인생영화 교체
	@PutMapping("/favorite/{movie_id}/{member_id}")
	public void postFavoriteExecute(@PathVariable("movie_id") int movie_id, @PathVariable("member_id") int member_id) {
		listService.postFavorite(movie_id, member_id);
	}
	


	
	// 코멘트 > 좋아요
	@GetMapping("/likes/{movie_id}/{member_id}")
	public List<LikesDTO> getLikesExecute(@PathVariable("movie_id") int movie_id, @PathVariable("member_id") int member_id) {
		System.out.println("멤버아이디" + member_id);
		System.out.println("좋아요" + listService.findLikesByIdProcess(movie_id, member_id));
		return listService.findLikesByIdProcess(movie_id, member_id);
	}
	
	@PostMapping("/likes")
	public void postLikesExecute(@RequestBody LikesDTO likes) {
		listService.postLikesProcess(likes);
	}
	
	@DeleteMapping("/likes")
	public void deleteLikesExecute(@RequestBody LikesDTO likes) {
		listService.deleteLikesProcess(likes);
	}
	
	
	
	// 신고
	@PutMapping("/spoiler")
	public void commentSpoilerReportExecute(@RequestBody ReviewInfoDTO review) {
		listService.commentSpoilerReport(review);
	}
	
	@PutMapping("/profanity")
	public void commentProfanityReportExecute(@RequestBody ReviewInfoDTO review) {
		listService.commentProfanityReport(review);
	}
	
	@GetMapping("/checkReported/{movie_id}/{member_id}")
	public ResponseEntity<Map<String, Boolean>> checkReportedExecute(@PathVariable("movie_id") int movie_id, @PathVariable("member_id") int member_id) {
		List<CommentsDTO> comment = listService.checkReported(movie_id, member_id);
		System.out.println(comment);
		boolean isReported = !comment.isEmpty();
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("isReported", isReported);
		
		return ResponseEntity.ok(response);
	}
	
	
	
}
