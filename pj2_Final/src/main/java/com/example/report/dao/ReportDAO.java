package com.example.report.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.review.dto.CommentsDTO;
import com.example.review.dto.ReportDTO;
import com.example.review.dto.ReviewInfoDTO;

@Mapper
@Repository
public interface ReportDAO {
	public List<ReportDTO> printReport();
	public void deleteReport(ReviewInfoDTO dto);
	public void spoiler(ReviewInfoDTO dto);
	
	public void returnReport(ReviewInfoDTO dto);
}
