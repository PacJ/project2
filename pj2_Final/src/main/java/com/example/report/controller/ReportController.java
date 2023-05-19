package com.example.report.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.dto.AdminDTO;
import com.example.report.service.ReportService;
import com.example.review.dto.CommentsDTO;
import com.example.review.dto.ReportDTO;
import com.example.review.dto.ReviewInfoDTO;

@CrossOrigin("*")
@RestController
public class ReportController {
	
	
	@Autowired
	private ReportService service;
	
	
	
	@PostMapping(value = "/reportpage")
	public List<ReportDTO> printReport() {
		System.out.println("reportpage");
		return service.printReportProcess();
	}
	
	@DeleteMapping(value = "/deletereport/{movie_id}/{member_id}")
	public String deleteReport( @PathVariable String member_id,@PathVariable String movie_id) {
		System.out.println("deletereport");
		
		ReviewInfoDTO dto= new ReviewInfoDTO();
		dto.setMember_id(member_id);
		dto.setMovie_id(movie_id);
		service.deleteReportProcess(dto);
		return "삭제";
	}
	
	@PostMapping(value= "/spoilerreport")
	public String spoiler(@RequestBody ReviewInfoDTO dto) {
		System.out.println(dto.member_id);
		System.out.println(dto.movie_id);
		
		service.spoilerProcess(dto);
		return "처리";
	}
	
	@PostMapping(value="/returnreport")
	public String returnre(@RequestBody ReviewInfoDTO dto) {
		
		System.out.println("returnreport");
		service.returnReportProcess(dto);
		return "반려";
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
