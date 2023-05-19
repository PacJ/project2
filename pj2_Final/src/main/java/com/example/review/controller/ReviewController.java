package com.example.review.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.list.dto.ListDTO;
import com.example.review.service.ReviewService;

@CrossOrigin({ "http://localhost:3000" })
@RestController
public class ReviewController {
	@Autowired
	private ReviewService reviewService;

	public ReviewController() {
		// TODO Auto-generated constructor stub
	}


	@PostMapping("/printmovie")
	public List<ListDTO> printmove(@RequestBody String eventKey) {
		System.out.println("printmovie요청들어옴");
		System.out.println(eventKey.toString());
		String genre=eventKey.replaceAll("\"", "");
		System.out.println(genre);
		List<ListDTO> aList=null;
		if(genre.equals("랜덤영화")){
			aList=reviewService.printRandomProcess();
		}else {
			aList=reviewService.printMovieProcess(genre);
			
		}
		return aList;
	}

	@GetMapping("/printrandom")
	public List<ListDTO> printrandom() {
		return reviewService.printRandomProcess();
	}
	

	@PostMapping("/recommend")
	public List<ListDTO> recommend(){
		List<ListDTO> aList=reviewService.testprocess();
		System.out.println(aList.size());
		return aList;
	}
	
	@PostMapping("/recommendtest")
	public void python(@RequestBody Map<String, List<String>> data) {
		System.out.println("recommend실행");

		List<String> recommendByGenre = data.get("recommendbygenre");
		List<String> titlesim = data.get("titlesim");
		List<String> userRecommend = data.get("userrecommend");
		for (int i = 0; i < recommendByGenre.size(); i++) {
			System.out.println(recommendByGenre.get(i));
		}

		System.out.println(data);
	}



}
