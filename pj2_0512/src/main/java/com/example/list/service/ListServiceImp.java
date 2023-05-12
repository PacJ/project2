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
import com.example.list.dto.RecommendDTO;
import com.example.member.dto.MemberDTO;
import com.example.review.dto.CommentsDTO;
import com.example.review.dto.LikesDTO;
import com.example.review.dto.RatingDTO;
import com.example.review.dto.ReviewDTO;
import com.example.review.dto.ReviewInfoDTO;
import com.example.wish.dto.WishDTO;

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
	
	
	
	// 시청한 영화 List
	@Override
	public ContentsDTO selectLastSeenProcess(int member_id) {
		return listDAO.selectLastSeen(member_id);
	}

	@Override
	public RecommendDTO movieRecProcess(int movie_id) {
		return listDAO.movieRec(movie_id);
	}

	@Override
	public RecommendDTO selectByIdProcess(int member_id) {
		return listDAO.selectById(member_id);
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
		contents.setListDTO(listDAO.getSimilarMovies(movie_id));
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
	@Transactional
	public void postCommentProcess(CommentsDTO comment) {
		listDAO.postComment(comment);
	}

	@Override
	@Transactional
	public void updateCommentProcess(CommentsDTO comment) {
		listDAO.updateComment(comment);
	}
	
	@Override
	@Transactional
	public void deleteCommentProcess(int movie_id, int member_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("movie_id", movie_id);
		map.put("member_id", member_id);
		
		listDAO.deleteComment(map);
	}
	
	
	
	// 별점
	@Override
	@Transactional
	public void postRatingProcess(RatingDTO rating) {
		listDAO.postRating(rating);
		ListDTO list = listDAO.calculateRating(rating.getMovie_id());
	}

	@Override
	@Transactional
	public void updateRatingProcess(RatingDTO rating) {
		listDAO.updateRating(rating);
	}
	
	@Override
	@Transactional
	public void deleteRatingProcess(int movie_id, int member_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("movie_id", movie_id);
		map.put("member_id", member_id);
		
		listDAO.deleteRating(map);
	}
	
	

	
	// 보고싶어요
	@Override
	public WishDTO findWishByIdProcess(int movie_id, int member_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("movie_id", movie_id);
		map.put("member_id", member_id);
		
		return listDAO.findWishById(map);
	}

	@Override
	@Transactional
	public void postWishProcess(WishDTO wish) {
		listDAO.postWish(wish);
	}

	@Override
	@Transactional
	public void deleteWishProcess(int movie_id, int member_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("movie_id", movie_id);
		map.put("member_id", member_id);
		
		listDAO.deleteWish(map);
	}

	
	
	// 코멘트 > 좋아요
	@Override
	public List<LikesDTO> findLikesByIdProcess(int movie_id, int member_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("movie_id", movie_id);
		map.put("member_id", member_id);
		
		return listDAO.findLikesById(map);
	}

	// 하나의 코멘트에 좋아요를 누를 때 > comments, likes, member table 업데이트
	@Override
	@Transactional
	public void postLikesProcess(LikesDTO likes) {
		// 1. likes table > insert
		listDAO.postLikes(likes);
		
		// 2. comments table > likes update
		CommentsDTO comment = new CommentsDTO();
		comment.setMovie_id(likes.getMovie_id());
		comment.setMember_id(likes.getComment_member_id());
		listDAO.incrementLikesById(comment);
		
		// 3. member table > likes_count update
		listDAO.incrementLikesCountById(likes.getComment_member_id());
		
		// 4. member table > grade update
		MemberDTO member = listDAO.findMemberById(likes.getComment_member_id());
		int grade = determineGrade(member.getLikes_count());
		member.setGrade(grade);
		listDAO.updateGradeById(member);
	}

	// grade 계산 메소드
	private int determineGrade(int likes_count) {
	    if (likes_count >= 201) {
	        return 1;
	    } else if (likes_count >= 101) {
	        return 2;
	    } else if (likes_count >= 51) {
	        return 3;
	    } else if (likes_count >= 21) {
	        return 4;
	    } else if (likes_count >= 1) {
	        return 5;
	    } else {
	        return 6;
	    }
	}

	@Override
	@Transactional
	public void deleteLikesProcess(LikesDTO likes) {
		// 1. likes table > delete
		listDAO.deleteLikes(likes);
		
		// 2. comments table > likes update
		CommentsDTO comment = new CommentsDTO();
		comment.setMovie_id(likes.getMovie_id());
		comment.setMember_id(likes.getComment_member_id());
		listDAO.decrementLikesById(comment);
		
		// 3. member table > likes_count update
		listDAO.decrementLikesCountById(likes.getComment_member_id());
		
		// 4. member table > grade update
		MemberDTO member = listDAO.findMemberById(likes.getComment_member_id());
		int grade = determineGrade(member.getLikes_count());
		member.setGrade(grade);
		listDAO.updateGradeById(member);
	}
	
	
	
	// 신고
	@Override
	@Transactional
	public void commentSpoilerReport(ReviewInfoDTO review) {
		listDAO.commentSpoilerReport(review);
	}

	@Override
	@Transactional
	public void commentProfanityReport(ReviewInfoDTO review) {
		listDAO.commentProfanityReport(review);
	}

	@Override
	public List<CommentsDTO> checkReported(int movie_id, int member_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("movie_id", movie_id);
		map.put("member_id", member_id);
		
		return listDAO.checkReported(map);
	}
	
	

	// 인생영화
	@Override
	public ContentsDTO findFavoriteByIdProcess(int member_id) {
		return listDAO.findFavoriteById(member_id);
	}

	@Override
	public void deleteFavorite(int member_id) {
		listDAO.deleteFavorite(member_id);
	}

	@Override
	public void postFavorite(int movie_id, int member_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("movie_id", movie_id);
		map.put("member_id", member_id);
		
		listDAO.postFavorite(map);
	}


}
