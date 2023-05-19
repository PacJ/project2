package com.example.report.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.report.dao.ReportDAO;
import com.example.review.dto.CommentsDTO;
import com.example.review.dto.ReportDTO;
import com.example.review.dto.ReviewInfoDTO;

@Service
public class ReportServiceImp implements ReportService{
	
	@Autowired
	private ReportDAO dao;
	
	
	@Override
	public List<ReportDTO> printReportProcess() {
		// TODO Auto-generated method stub
		return dao.printReport();
	}
	
	@Override
	public void deleteReportProcess(ReviewInfoDTO dto) {
		// TODO Auto-generated method stub
		
		dao.deleteReport(dto);
	}
	
	@Override
	public void spoilerProcess(ReviewInfoDTO dto) {
		// TODO Auto-generated method stub
		dao.spoiler(dto);
	}
	@Override
	public void returnReportProcess(ReviewInfoDTO dto) {
		// TODO Auto-generated method stub
		dao.returnReport(dto);
	}
	
	
	
	
}
