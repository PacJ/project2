package com.example.review.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {
	private int movie_id;
	private int member_id;
	private Double rating;
	private String content;
	private int likes;
	private Date regdate;
	private Date moddate;
	private int state;
	
	private String nickname;
	private String profile_path;
	private int grade;
}
