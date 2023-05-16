package com.example.analysis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SimilarMemberDTO {
	private int member_id;
	private String nickname;
	private int age;
	private int gender;
	private int likes_count;
	private int grade;
	private int visibility;
	private int common_genres;
	private String profile_path;
}
