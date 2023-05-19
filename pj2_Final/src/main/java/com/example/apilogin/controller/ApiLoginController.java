package com.example.apilogin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.apilogin.service.ApiLoginService;
import com.example.member.dto.MemberDTO;


import oracle.jdbc.proxy.annotation.Post;


@CrossOrigin({ "http://localhost:3000" })
@RestController
public class ApiLoginController {
	@Autowired
	private ApiLoginService apiloginservice;
	
	@Autowired
	private BCryptPasswordEncoder encodePassword;
	
	
	public ApiLoginController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/kakaoRegister")
	public MemberDTO addKakaoMember(@RequestBody Map<String, String> a ) {
		MemberDTO dto= new MemberDTO();
		System.out.println("kakaoregister");		
		String email =a.get("email");
		String nickname=a.get("nickname");
		String gender=a.get("gender");
		String age_range= a.get("age_range");
		
		int count =apiloginservice.countKakaoMemberProcess(email);
		if(count > 0) {
			System.out.println("아이디있음");
			MemberDTO b= apiloginservice.selectKakaoMemberProcess(email);
			System.out.println(b.getPassword());
			return b;
		}else {
			
			
			if("female".equals(gender)) {
				dto.setGender(2);
			}else {
				dto.setGender(1);
			}
			String avgage= age_range.substring(0,1)+"5";
			
			dto.setEmail(email);
			dto.setNickname(nickname);
			dto.setAge(Integer.parseInt(avgage));
			dto.setPassword(encodePassword.encode(a.get("password")));
			
			apiloginservice.insertKakaoMemberProcess(dto);
			
			System.out.println(email);
			MemberDTO b= apiloginservice.selectKakaoMemberProcess(email);
			return b;
			
		}
		
		

	}
	@PostMapping("/naverregister")
	public MemberDTO addNaverMember(@RequestBody Map<String, String> a ) {
		MemberDTO dto= new MemberDTO();
		System.out.println("naverregister");		
		String email =a.get("email");
		String nickname=a.get("nickname");
		String gender=a.get("gender");
		String name= a.get("name");
		String age_range= a.get("age_range");
		System.out.println(email);
		
		int count =apiloginservice.countNaverMemberProcess(email);
		if(count > 0) {
			System.out.println("아이디있음");
			MemberDTO b= apiloginservice.selectNaverMemberProcess(email);
			System.out.println(b.getPassword());
			return b;
		}else {
			
			
			if("M".equals(gender)) {
				dto.setGender(1);
			}else {
				dto.setGender(2);
			}
			String avgage= age_range.substring(0,1)+"5";
			
			dto.setEmail(email);
			dto.setNickname(nickname);
			dto.setAge(Integer.parseInt(avgage));
			dto.setPassword(encodePassword.encode(a.get("password")));
			dto.setName(name);
			apiloginservice.insertNaverMemberProcess(dto);
			
			System.out.println(email);
			MemberDTO b= apiloginservice.selectNaverMemberProcess(email);
			return b;
			
		}

	}
	
	@PostMapping("/test")
	public String test() {
		
		System.out.println("test");
		return "Test";
	}
	
	
	@PostMapping("/check")
	public int test(@RequestBody String member_id) {
		
		System.out.println("check");
		int a = apiloginservice.checkcountProcess(member_id);
		System.out.println(member_id);
		return a;
	}
}
