package com.example.list.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.actor.dto.ActorDTO;
import com.example.director.dto.DirectorDTO;
import com.example.genre.dto.GenreDTO;
import com.example.list.dto.ContentsDTO;
import com.example.list.dto.ImagesDTO;
import com.example.list.dto.ListDTO;
import com.example.list.dto.RecommendDTO;
import com.example.member.dto.MemberDTO;
import com.example.review.dto.CommentsDTO;
import com.example.review.dto.LikesDTO;
import com.example.review.dto.RatingDTO;
import com.example.review.dto.ReviewDTO;
import com.example.review.dto.ReviewInfoDTO;
import com.example.wish.dto.WishDTO;

@Mapper
@Repository
public interface ListDAO {
	public List<ListDTO> getTopRatedMovies();
	public List<ListDTO> getTopRated();
	public List<ListDTO> getLatestMovies();
	public List<ListDTO> getThemeMovies();
	public List<ListDTO> getTopRatedDirector();
	public List<ListDTO> getTopRatedActor();
	
	public List<ListDTO> getFavoriteGenre(int member_id);
	public List<ListDTO> getFavoriteDirector();
	public List<ListDTO> getFavoriteActor();
	
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
	
	//시청 영화(후기를 남긴 영화)
	public ContentsDTO selectLastSeen(int member_id);
	
	//추천 영화 선택(movie_id, 리뷰 없는 row만 출력. Review 있으면 null)
	public RecommendDTO movieRec(int movie_id);
	
	//ID로 영화 정보 선택
	public RecommendDTO selectById(int member_id);
	
	// 영화 상세 페이지
	public ContentsDTO getMovieById(int movie_id);
	public List<GenreDTO> getGenresByMovieId(int movie_id);
	public List<DirectorDTO> getDirectorsByMovieId(int movie_id);
	public List<ActorDTO> getActorsByMovieId(int movie_id);
	public List<ImagesDTO> getImagesByMovieId(int movie_id);
	public List<ReviewDTO> getReviewsByMovieId(int movie_id);
	public List<ListDTO> getSimilarMovies(int movie_id);
	public String getTrailerByMovieId(int movie_id);
	
	// review > comment, rating
	public void postComment(CommentsDTO comment);
	public void updateComment(CommentsDTO comment);
	public void deleteComment(Map<String, Object> map);
	public void postRating(RatingDTO rating);
	public void updateRating(RatingDTO rating);
	public void deleteRating(Map<String, Object> map);
	public ReviewDTO findReviewById(Map<String, Object> map);
	
	// 부귀영화 평점 업데이트
	public Map<String, Object> calculateRating(int movie_id);
	public void updateMovieRating(ListDTO list);
	
	// wish
	public WishDTO findWishById(Map<String, Object> map);
	public void postWish(WishDTO wish);
	public void deleteWish(Map<String, Object> map);
	
	// likes
	public List<LikesDTO> findLikesById(Map<String, Object> map);
	public void postLikes(LikesDTO likes);
	public void incrementLikesById(CommentsDTO comment);
	public void incrementLikesCountById(int member_id);
	public void deleteLikes(LikesDTO likes);
	public void decrementLikesById(CommentsDTO comment);
	public void decrementLikesCountById(int member_id);
	public void updateGradeById(MemberDTO member);
	public MemberDTO findMemberById(int member_id);
	
	// 신고
	public void commentSpoilerReport(ReviewInfoDTO review);
	public void commentProfanityReport(ReviewInfoDTO review);
	public List<CommentsDTO> checkReported(Map<String, Object> map);
	
	// 인생영화
	public ContentsDTO findFavoriteById(int member_id);
	public void deleteFavorite(int member_id);
	public void postFavorite(Map<String, Object> map);
	
}
