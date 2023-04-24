package com.example.wish.dto;

import java.sql.Date;

public class WishDTO {
	private int member_id;
	private int movie_id;
	private Date regDate;
	
	public WishDTO() {
		// TODO Auto-generated constructor stub
	}

	public WishDTO(int member_id, int movie_id, Date regDate) {
		super();
		this.member_id = member_id;
		this.movie_id = movie_id;
		this.regDate = regDate;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public int getMovie_id() {
		return movie_id;
	}

	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
}
