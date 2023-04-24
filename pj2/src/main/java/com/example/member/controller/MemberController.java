package com.example.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.member.dto.MemberDTO;
import com.example.member.service.MemberService;
import com.example.list.dto.ListDTO;


@CrossOrigin("*")
@RestController
public class MemberController {
	
	@Autowired
	MemberService memberService;
	
	@GetMapping("/printrandom") // json객체를 java객체로 변환해야하기 때문에 requestbody사용
	public List<ListDTO> printRandom() {
		System.out.println("요청 들어옴");
		List<ListDTO> aList= memberService.randomService();
		System.out.println(aList.size());
		return aList; 
	}
	

	
}
