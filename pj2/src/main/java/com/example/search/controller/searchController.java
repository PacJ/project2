package com.example.search.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.actor.dto.ActorDTO;
import com.example.director.dto.DirectorDTO;
import com.example.list.dto.ListDTO;
import com.example.list.service.ListService;

@CrossOrigin("*")
@RestController
public class searchController {

	@Autowired
	private ListService listService;
	
	public searchController() {
		
	}
	
//	검색 시에 영화, 감독, 배우 이름으로 결과값 return.
	@GetMapping("/search/{query}")
	public Map<String, Object> movieExecute(@PathVariable("query") String query) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchMovieList", listService.searchMovies(query.toUpperCase()));
		map.put("searchActorList", listService.searchActors(query.toUpperCase()));
		map.put("searchDirectorList", listService.searchDirectors(query.toUpperCase()));
		System.out.println(query.toUpperCase());
		
		return map;
	}
	
//	자동완성을 위한 모든 영화/감독/배우 이름 return
	@GetMapping("/selectAll")
	public List<String> autocomplete() {
		return listService.selectAllProcess();
	}
	
//	배우 프로필(id값으로 검색) return
	@GetMapping("/actorProfile/{id}")
	public Map<String, Object> ActorProfileExecute(@PathVariable("id") String actor_id) {
		Map<String, Object> map = new HashMap<>();
//		System.out.println(actor_id);
		map.put("castMovieList", listService.castMoviesProcess(actor_id));
		map.put("actorInfo", listService.selectByActorIdProcess(actor_id));
		
		return map;
	}
	
//	감독 프로필(id값으로 검색) return
	@GetMapping("/dirProfile/{id}")
	public Map<String, Object> DirectorProfileExecute(@PathVariable("id") String dir_id) {
		Map<String, Object> map = new HashMap<>();
//		System.out.println(dir_id);
		map.put("dirMovieList", listService.dirMoviesProcess(dir_id));
		map.put("dirInfo", listService.selectByDirIdProcess(dir_id));

		return map;
	}
	
}
