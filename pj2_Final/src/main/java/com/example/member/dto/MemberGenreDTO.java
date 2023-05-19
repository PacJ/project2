package com.example.member.dto;



import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberGenreDTO {
	
	private List<Integer> selectedGenre;
	private int memberId;
	private int genreId;
	
	

}
