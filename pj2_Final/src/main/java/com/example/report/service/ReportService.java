package com.example.report.service;

import java.util.List;
import java.util.Map;

import com.example.review.dto.CommentsDTO;
import com.example.review.dto.ReportDTO;
import com.example.review.dto.ReviewInfoDTO;

public interface ReportService {
	public List<ReportDTO> printReportProcess();
	public void deleteReportProcess(ReviewInfoDTO dto);
	public void spoilerProcess(ReviewInfoDTO dto);
	public void returnReportProcess(ReviewInfoDTO dto);

}
