package com.example.review.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//create table comments (
//	    movie_id number,
//	    member_id number,
//	    content varchar2(4000),
//	    likes number,
//	    regdate date,
//	    moddate date,
//	    state varchar2(2) --1기본2블러3삭제
//	);

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentsDTO {
	private int movie_id;
	private int member_id;
	private String content;
	private Date regdate;
	private Date moddate;
	private int state;
}
