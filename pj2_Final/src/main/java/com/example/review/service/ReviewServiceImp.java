package com.example.review.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.list.dto.ListDTO;
import com.example.review.dao.ReviewDAO;

@Service
public class ReviewServiceImp implements ReviewService{
	
	@Autowired
	private ReviewDAO reviewDAO;
	
	@Override
	public void recommendByCosProcess(String title) {
		// TODO Auto-generated method stub
		reviewDAO.recommendByCos(title);
	}

	@Override
	public void recommendByGenreProcess(String movie_id) {
		// TODO Auto-generated method stub
		reviewDAO.recommendByGenre(movie_id);
	}@Override
	public void recommendByTitleProcess(String movie_id) {
		// TODO Auto-generated method stub
		reviewDAO.recommendByTitle(movie_id);
	}
	@Override
	public List<ListDTO> printRandomProcess() {
		// TODO Auto-generated method stub
		return reviewDAO.printRandomReview();
	}
	@Override
	public List<ListDTO> printMovieProcess(String genre) {
		// TODO Auto-generated method stub
		return reviewDAO.printMovie(genre);
	}
	@Override
	public List<ListDTO> testprocess() {
		// TODO Auto-generated method stub
		return reviewDAO.test();
	}
	
}
