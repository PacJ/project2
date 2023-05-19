package com.example.review.service;

import java.util.List;

import com.example.list.dto.ListDTO;

public interface ReviewService {
	public void recommendByTitleProcess(String movie_id);
	public void recommendByGenreProcess(String movie_id);
	public void recommendByCosProcess(String title);
	
	public List<ListDTO> printRandomProcess();
	public List<ListDTO> printMovieProcess(String genre);
	public List<ListDTO> testprocess();
	
}
