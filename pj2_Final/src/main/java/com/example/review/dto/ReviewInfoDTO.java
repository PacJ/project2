package com.example.review.dto;

public class ReviewInfoDTO {
	public String member_id;
	public String movie_id;
	
	public ReviewInfoDTO() {
		// TODO Auto-generated constructor stub
	}
	public ReviewInfoDTO(String member_id, String movie_id) {
		super();
		this.member_id = member_id;
		this.movie_id = movie_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(String movie_id) {
		this.movie_id = movie_id;
	}
	
	
	
}
