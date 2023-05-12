package com.example.recommend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.list.dto.ListDTO;
import com.example.recommend.service.RecommendService;
import java.io.IOException;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
@CrossOrigin({ "http://localhost:3000" })
@RestController
public class RecommendController {
	@Autowired
	private RecommendService recommendService;

	public RecommendController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/abc")
	public Map<String, Object> recommend(@RequestBody String member_id) {
		System.out.println("recommend 페이지");
		Map<String, Object> resultMap = new HashMap<>();
		List<ListDTO> aList = recommendService.recentmoviesProcess(member_id);
		List<Integer> array=new ArrayList<Integer>();
		System.out.println(aList.size());
		
		for (int i = 0; i < 3; i++) {
			System.out.println(aList.get(i).getTitle());
			array.add(aList.get(i).getMovie_id());
		}
		
		System.out.println("array"+array.toString());
		String url = "http://localhost:5000/abc/"+member_id+"/"+array.toString();

		// RestTemplate 생성
		RestTemplate restTemplate = new RestTemplate();

		// HttpHeader 객체 생성
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// HttpEntity 객체 생성
		HttpEntity<String> entity = new HttpEntity<String>(headers);

		// 요청 보내고 응답 받기
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
		List<Integer> recommendByGenre=null;
		List<Integer> titleSim=null;
		List<Integer> userRecommend=null;
		// 응답 본문 출력
		System.out.println(response.getBody());
		String json = response.getBody();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode;
		try {
			rootNode = objectMapper.readTree(json);
			JsonNode recommendByGenreNode = rootNode.get("recommendbygenre");
			JsonNode titleSimNode = rootNode.get("titlesim");
			JsonNode userRecommendNode = rootNode.get("userrecommend");

			recommendByGenre = objectMapper.convertValue(recommendByGenreNode, new TypeReference<List<Integer>>() {});
			titleSim = objectMapper.convertValue(titleSimNode, new TypeReference<List<Integer>>() {});
			userRecommend = objectMapper.convertValue(userRecommendNode, new TypeReference<List<Integer>>() {});

		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ListDTO a=new ListDTO();
		ListDTO b=new ListDTO();
		ListDTO c=new ListDTO();
		List<ListDTO> rbGenre=new ArrayList<ListDTO> ();
		List<ListDTO> tSim=new ArrayList<ListDTO> ();
		List<ListDTO> uRecommend=new ArrayList<ListDTO> ();
		
		for (int i =0; i < 10;i++) {
			System.out.println();
			a=recommendService.getmovieProcess(recommendByGenre.get(i).toString());
			rbGenre.add(a);
			b=recommendService.getmovieProcess(titleSim.get(i).toString());
			tSim.add(b);
			c=recommendService.getmovieProcess(userRecommend.get(i).toString());
			uRecommend.add(c);
			System.out.println(a+","+b+","+c);
		}
		resultMap.put("recommendByGenre", rbGenre);
		resultMap.put("titleSim", tSim);
		resultMap.put("userRecommend", uRecommend);
		 
		return resultMap;
	}
}
