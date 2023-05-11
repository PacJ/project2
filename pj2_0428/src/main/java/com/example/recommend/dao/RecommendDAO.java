package com.example.recommend.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.list.dto.ListDTO;

@Mapper
@Repository
public interface RecommendDAO {
	public List<ListDTO> recentmovies(String member_id);
	public ListDTO getmovie(String movie_id);
}
