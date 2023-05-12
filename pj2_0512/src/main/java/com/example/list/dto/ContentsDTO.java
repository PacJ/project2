package com.example.list.dto;

import java.sql.Date;
import java.util.List;

import com.example.actor.dto.ActorDTO;
import com.example.director.dto.DirectorDTO;
import com.example.genre.dto.GenreDTO;
import com.example.review.dto.ReviewDTO;
import com.example.wish.dto.WishDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContentsDTO {
	private int movie_id;
	private String title;
	private String original_title;
	private Date release_date;
	private int popularity;
	private Double tmdb_vote_sum;
	private int tmdb_vote_cnt;
	private Double vote_sum;
	private int vote_cnt;
	private String overView;
	private String poster_path;
	private String backdrop_path;
	private String country;
	private int runtime;
	
	// actor, director, genre, image, review, list 들어가야함
	private List<ActorDTO> actorDTO;
	private List<DirectorDTO> directorDTO;
	private List<GenreDTO> genreDTO;
	private List<ImagesDTO> imagesDTO;
	private List<ReviewDTO> reviewDTO;
	private List<ListDTO> listDTO;
}
