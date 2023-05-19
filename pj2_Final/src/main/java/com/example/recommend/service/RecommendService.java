package com.example.recommend.service;

import java.util.List;

import com.example.list.dto.ListDTO;

public interface RecommendService {
	public List<ListDTO> recentmoviesProcess(String member_id);
	public ListDTO getmovieProcess(String movie_id);
}
