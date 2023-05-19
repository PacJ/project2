package com.example.analysis.service;

import java.util.List;

import com.example.analysis.dto.AnalysisDTO;
import com.example.analysis.dto.SimilarMemberDTO;

public interface AnalysisService {

	//선호장르
	public List<AnalysisDTO> getRatingDistributionProcess(int member_id);
	public List<AnalysisDTO>  getPreferredGenreProcess(int member_id);
	public List<AnalysisDTO>  getPreferredDirectorProcess(int member_id);
	public List<AnalysisDTO>  getPreferredActorProcess(int member_id);
	
	//비슷한 취향을 가진 유저 리스트
	public List<SimilarMemberDTO> getSimilarMembers(int member_id);
}
