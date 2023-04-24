package com.example.review.dto;

import java.sql.Date;

public class ReviewDTO {
	private int movie_id;
	private int member_id;
	private int rating;
	private String content;
	private int likes;
	private Date regDate;
	private Date modDate;
	private String state;
	
	public ReviewDTO() {
		// TODO Auto-generated constructor stub
	}

	public ReviewDTO(int movie_id, int member_id, int rating, String content, int likes, Date regDate, Date modDate,
			String state) {
		super();
		this.movie_id = movie_id;
		this.member_id = member_id;
		this.rating = rating;
		this.content = content;
		this.likes = likes;
		this.regDate = regDate;
		this.modDate = modDate;
		this.state = state;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}
