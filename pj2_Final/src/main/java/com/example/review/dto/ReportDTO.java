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
public class ReportDTO {
	
	private int movie_id;
	private String title;
	private int member_id;
	private String nickname;
	private String content;
	private Date regdate;
	private Date moddate;
	private int state;
}
