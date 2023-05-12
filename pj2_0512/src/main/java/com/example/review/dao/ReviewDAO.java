package com.example.review.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.list.dto.ListDTO;
@Mapper
@Repository
public interface ReviewDAO {
	
	public ListDTO recommendByTitle(String movie_id);
	public ListDTO recommendByGenre(String movie_id);
	public ListDTO recommendByCos(String title);
	
	public List<ListDTO> printRandomReview();
	public List<ListDTO> printMovie(String genre);
	public List<ListDTO> test();
}
