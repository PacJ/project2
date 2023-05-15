package com.example.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.dto.AdminDTO;

import com.example.admin.service.AdminService;
import com.example.review.dto.ReviewInfoDTO;

import aj.org.objectweb.asm.Type;

//http://localhost:8090/myapp/member/signup.do
//@CrossOrigin(origins={"http://localhost:3000"})
@CrossOrigin("*")
@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	@Autowired
	private BCryptPasswordEncoder encodePassword;

	public AdminController() {

	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	// 회원가입처리
	@PostMapping(value = "/adminregister")
	public String addMember(@RequestBody AdminDTO adminDTO, HttpSession session) {
		adminDTO.setPassword(encodePassword.encode(adminDTO.getPassword()));
		adminService.addAdminProcess(adminDTO);

		return null;
	}

	// 이메일 중복 확인을 위한 Controller 함수
	@PostMapping(value = "/admin/idcheck")
	public int idCheck(@RequestBody String email) {
		int result = adminService.idcheckservcie(email.replaceAll("\"",""));  
		return result;
	}
 
    //관리자정보 수정
    @PostMapping("/admin/update")//json객체를 java객체로 변환해야하기 때문에 requestbody사용 
    public void updateAdmin(@RequestBody AdminDTO adminDTO) {
    	System.out.println("이름:" + adminDTO.getName());
    	adminDTO.setPassword(encodePassword.encode(adminDTO.getPassword()));
    	adminService.updateNameProcess(adminDTO);
    }

    //관리자페이지(댓글 삭제 및 수정 )
    @GetMapping("/adminpage")
    public Map<String, Object> adminPage() {
    	Map<String, Object> map=new HashMap<>();
    	map.put("editSpoiler", adminService.editSpoilerProcess());
    	map.put("editReport", adminService.editReportProcess());
    	return map;
    }

   	//신고된 댓글 블러 처리해주기 state2(스포일 신고당한 댓글 )=> state4로 변경(블러 처리가 되어진 스포일 댓글 ) 처리 
    @PutMapping("/blur")
   	public void blurspoiler (@RequestBody ReviewInfoDTO info) {
    	adminService.blurSpoilerprocess(info);
  	}
    
    //신고된 댓글 삭제 처리해주기
    @DeleteMapping("/deletereview")
   	public void deletereview (@RequestBody ReviewInfoDTO info) {
    	adminService.deleteReviewprocess(info);
    	//axios 오류가 뜨면 react//adminpage에 있는 axios.@@@@랑 확인해봐야함 같아야 하기 떄문
  	}
   	
	
    	
//    	adminService.editSpoilerProcess(2);
//    	adminService.editReportProcess(3);
//에딧 리포트, 스포일러 안에 어드민 서비스에 있는 에딧 스포일러 @#@# 리스트들이 저장됨.  맵에다가 추가해라 
//map 이 리스트를 담아주는 역할  어드민 댓글 관리 같은 경우는 2,3 상태의 리스트를 나열해야하기 때문에 map으로 묶어줘야 한다. 
//hashmap key values 값으로 묶어서 저장해주는 맵의 형태 

}
    
    

