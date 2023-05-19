package com.example.analysis.dto;

import java.util.List;

import com.example.genre.dto.GenreDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnalysisDTO {

	// 배우이름
	private String actor_name;

	// 배우이미지
	private String actor_image;

	// 배우별 평점울 준 총합 (평가 갯수)
	private int actorRating_cnt;
	
	// 감독이름
	private String director_name;

	// 감독 사진
	private String director_image;

	// 감독별 평점울 준 총합 (평가 갯수)
	private int directorRating_cnt;
	

	private String genre_name;

	private int total;

	private GenreDTO genreDTO;

	// 장르별 평점울 준 총합 (평가 갯수)
	private int genreRating_cnt;

	// 평점별 갯수 (0.5~5 순)
	private int rating_cnt;

	// 별점 종류
	private double rating;

}
