package com.example.analysis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.analysis.dao.AnalysisDAO;
import com.example.analysis.dto.AnalysisDTO;
import com.example.list.dao.ListDAO;

@Service
public class AnalysisServiceImp implements AnalysisService{

	@Autowired
	private AnalysisDAO analysisDAO;

	@Override
	public List<AnalysisDTO> getRatingDistributionProcess(int member_id) {
		
	
		return analysisDAO.getRatingDistribution(member_id);
	}

	@Override
	public List<AnalysisDTO> getPreferredGenreProcess(int member_id) {
		
		return analysisDAO.getPreferredGenre(member_id);
	}

	@Override
	public List<AnalysisDTO> getPreferredDirectorProcess(int member_id) {
		return analysisDAO.getPreferredDirector(member_id);
	}

	@Override
	public List<AnalysisDTO> getPreferredActorProcess(int member_id) {
		return analysisDAO.getPreferredActor(member_id);
	}

	


	
}
