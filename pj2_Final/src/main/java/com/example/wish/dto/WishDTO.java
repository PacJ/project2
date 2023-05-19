package com.example.wish.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class WishDTO {
	private int member_id;
	private int movie_id;
	private Date regdate;
	
}
