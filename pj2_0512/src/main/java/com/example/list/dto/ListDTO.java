package com.example.list.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListDTO {
	private int movie_id;
	private String title;
	private String original_title;
	private Date release_date;
//	private int popularity;
	private Double tmdb_vote_sum;
	private int tmdb_vote_cnt;
	private Double vote_sum;
	private int vote_cnt;
//	private String overView;
	private String poster_path;
//	private String backdrop_path;
	private String country;
//	private int runtime;
	
	
}
