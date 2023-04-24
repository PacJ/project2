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

@CrossOrigin({"http://localhost:3000"})
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
		System.out.println(listService.searchMovies(query));
		System.out.println(query);
		
		return map;
	}
	
}
