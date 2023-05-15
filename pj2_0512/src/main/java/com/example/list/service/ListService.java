package com.example.list.service;

import java.util.List;
import java.util.Map;

import com.example.actor.dto.ActorDTO;
import com.example.director.dto.DirectorDTO;
import com.example.list.dto.ContentsDTO;
import com.example.list.dto.ListDTO;
import com.example.list.dto.RecommendDTO;
import com.example.review.dto.CommentsDTO;
import com.example.review.dto.LikesDTO;
import com.example.review.dto.RatingDTO;
import com.example.review.dto.ReviewDTO;
import com.example.review.dto.ReviewInfoDTO;
import com.example.wish.dto.WishDTO;

public interface ListService {
	public List<ListDTO> getTopRatedMoviesProcess();
	public List<ListDTO> getTopRatedProcess();
	public List<ListDTO> getLatestMoviesProcess();
	public List<ListDTO> getThemeMoviesProcess();
	public List<ListDTO> getTopRatedDirectorProcess();
	public List<ListDTO> getTopRatedActorProcess();
	public List<ListDTO> getFavoriteGenreProcess(int member_id);
	public List<ListDTO> getFavoriteDirectorProcess();
	public List<ListDTO> getFavoriteActorProcess();
	
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
	
	//아이디로 영화 검색
	public RecommendDTO selectByIdProcess(int member_id);
	
	//시청한 영화(리뷰 남긴 영화)
	public ContentsDTO selectLastSeenProcess(int member_id);
	
	//추천 영화(리뷰를 남기지 않은 영화)
	public RecommendDTO movieRecProcess(int movie_id);
	
	// 영화 상세 페이지
	public ContentsDTO getContentsProcess(int movie_id);
	
	// review > comment, rating
	public void postCommentProcess(CommentsDTO comment);
	public void updateCommentProcess(CommentsDTO comment);
	public void deleteCommentProcess(int movie_id, int member_id);
	public void postRatingProcess(RatingDTO rating);
	public void updateRatingProcess(RatingDTO rating);
	public void deleteRatingProcess(int movie_id, int member_id);
	public ReviewDTO findReviewByIdProcess(int movie_id, int member_id);
		
	// wish
	public WishDTO findWishByIdProcess(int movie_id, int member_id);
	public void postWishProcess(WishDTO wish);
	public void deleteWishProcess(int movie_id, int member_id);
	
	// likes
	public List<LikesDTO> findLikesByIdProcess(int movie_id, int member_id);
	public void postLikesProcess(LikesDTO likes);
	public void deleteLikesProcess(LikesDTO likes);
	
	// 신고
	public void commentSpoilerReport(ReviewInfoDTO review);
	public void commentProfanityReport(ReviewInfoDTO review);
	public List<CommentsDTO> checkReported(int movie_id, int member_id);
	
	// 인생영화
	public ContentsDTO findFavoriteByIdProcess(int member_id);
	public void deleteFavorite(int member_id);
	public void postFavorite(int movie_id, int member_id);
	
}
