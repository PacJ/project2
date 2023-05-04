package com.example.list.controller;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
import com.example.list.dto.RecommendDTO;
import com.example.list.service.ListService;
import com.example.review.dto.CommentsDTO;
import com.example.review.dto.RatingDTO;
import com.example.review.dto.ReviewDTO;

@CrossOrigin({"*"})
@RestController
public class ListController {
	
	private static final Map<Integer, String> CACHE = new HashMap<>();
	
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
	
	// 코사인 유사도를 이용한 회원 시청영화(후기를 남긴 영화) 기반 영화추천(ㅅㅂ)
	@PostMapping("/recommend")
	public List<RecommendDTO> recommendMovie(HttpSession session, @RequestBody String movie_id) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
	    System.out.println("추천영화");
//	    System.out.println(listService.selectLastSeenProcess(1).getTitle());
//	    System.out.println(listService.selectLastSeenProcess(1).getOverView());
	    ContentsDTO lastMovie = listService.selectLastSeenProcess(1);
	    System.out.println(lastMovie);
	    System.out.println(movie_id);
	    System.out.println(movie_id.length());
	    System.out.println(movie_id.getClass());
	    String[] idString = movie_id.replaceAll("[^\\d,]","").split(",");
	    int[] movies = new int[idString.length];
	    for(int i = 0; i < idString.length; i++) {
	    	System.out.println(idString[i]);
	    	movies[i] = Integer.parseInt(idString[i]);
	    }
	    System.out.println(Arrays.toString(movies));
	    
	    List<RecommendDTO> recMovies = new ArrayList<RecommendDTO>();
	    for(int i = 0; i < 9; i++) {
	    	int recMovieId = movies[i];
	    	System.out.println(recMovieId);
	    	RecommendDTO recDto = listService.movieRecProcess(recMovieId);
	    	if (recDto != null) {
	    		System.out.println(recMovieId);
	    		recMovies.add(recDto);
	    	} else{ 
	    		System.out.println("이미 본 영화 " + recMovieId);
	    	
	    	}
	    }
	    System.out.println(recMovies);
	    
	    return recMovies;
//	    if(lastMovie != null) {
//	    		
//	    		int movie_id = lastMovie.getMovie_id();
//	    		int result;
//	    		int results[] = null;
//	    	if(CACHE.containsKey(movie_id)) {
//	    		String resultString = CACHE.get(movie_id);
//	    		result = Integer.parseInt(resultString);
//	    		System.out.println("캐시된 결과: " + result);
//	    		String[] dataArray = resultString.split(",");
//	    		results = new int[dataArray.length];
//	    		for(int i = 0; i < dataArray.length; i++) {
//	    			results[i] = Integer.parseInt(dataArray[i]);
////	    			System.out.println(dataArray[i]);
////	    			System.out.println(results[i]);
//	    		}
//	    	} else {
//	    		try {
//	    			URI uri = new URI("http://localhost:5000/recommend?movie_id=" + lastMovie.getMovie_id());
//	    			System.out.println(uri);
//	    			HttpGet httpGet = new HttpGet(uri);
////	    			System.out.println(httpGet);
//	    			CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
//	    			String responseString = EntityUtils.toString(httpResponse.getEntity());
////	    			System.out.println(responseString);
//	    			httpResponse.close();
//	    			CACHE.put(movie_id, responseString);
//	    			for(int i = 0; i <=CACHE.size(); i++) {
////	    				System.out.println(CACHE.get(responseString) + " " +  CACHE);
//	    			}
//	    		} catch (URISyntaxException e) {
//					System.err.println("잘못된 URI 구문: " + e.getMessage());
//				} catch (IOException e) {
//					System.err.println("I/O 오류 발생: " + e.getMessage());
//				}
//	    	}
//	    	
////	    	System.out.println(result.getClass().getName());
//	    
//	    	int[] dataArray = results;
//	    	
//	    	List<ContentsDTO> recList = new ArrayList<ContentsDTO>();
//	    	for(int i = 0; i <= 9; i++) {
//	    		int recId = results[i];
//	    		ContentsDTO movie = listService.movieRecProcess(recId);
//	    		if(movie != null) {
//	    			System.out.println(recId);
//	    			recList.add(movie);
//	    			System.out.println("안녕하세요");
//	    		}
//	    	}
//	    }
//	    ModelAndView mav = new ModelAndView("recommend");
//	    mav.addObject("seenMovies", seenMovies);
	}
	
}//end Controller
