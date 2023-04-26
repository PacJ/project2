package com.example.search.controller;

import java.util.HashMap;
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
	
	// http://localhost:8090/search/Kea
	@GetMapping("/search/{query}")
	public Map<String, Object> movieExecute(@PathVariable("query") String query) {
		Map<String, Object> map = new HashMap<>();
		map.put("searchMovieList", listService.searchMovies(query.toUpperCase()));
		map.put("searchActorList", listService.searchActors(query.toUpperCase()));
		map.put("searchDirectorList", listService.searchDirectors(query.toUpperCase()));
		System.out.println(listService.searchMovies(query.toUpperCase()).get(1).getPoster_path());
		System.out.println(listService.searchMovies(query.toUpperCase()).get(1).getPoster_path());
		System.out.println(query);
		
		return map;
	}
	
	@GetMapping("/castProfile/{actor_id}/{dir_id}")
	public Map<String, Object> castProfileExecute(@PathVariable("actor_id") String actor_id, @PathVariable("dir_id") String dir_id) {
		Map<String, Object> map = new HashMap<>();
		System.out.println(actor_id+"/////"+dir_id);
		System.out.println(listService.castMoviesProcess(actor_id));
		map.put("castMovieList", listService.castMoviesProcess(actor_id));
		if(dir_id.equals("undefined")) {
			
		}
		System.out.println(listService.dirMoviesProcess(dir_id));
		map.put("dirMovieList", listService.dirMoviesProcess(dir_id));

		return map;
	}
	
}
