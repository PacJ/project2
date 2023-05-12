package com.example.analysis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.analysis.dto.AnalysisDTO;

@Mapper
@Repository
public interface AnalysisDAO {

	public List<AnalysisDTO> getRatingDistribution(int member_id);
	
	public List<AnalysisDTO> getPreferredGenre(int member_id);
	
	public List<AnalysisDTO> getPreferredDirector(int member_id);
	
	public List<AnalysisDTO> getPreferredActor(int member_id);
}
