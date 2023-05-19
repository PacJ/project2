package com.example.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LikesDTO {
	private int member_id;
	private int movie_id;
	private int comment_member_id;
}
