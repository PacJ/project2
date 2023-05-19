package com.example.analysis.contraller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.analysis.dto.AnalysisDTO;
import com.example.analysis.dto.SimilarMemberDTO;
import com.example.analysis.service.AnalysisService;

@CrossOrigin("*")
@RestController
public class AnalysisController {
	@Autowired
	private AnalysisService analysisService;

	public AnalysisController() {
	}


	@GetMapping("/analysis/{member_id}")
	public Map<String, Object> tasteAnalysis(@PathVariable("member_id") int member_id) {

		Map<String, Object> map = new HashMap<>();
		
		double i = 0.5;
		int j = 0;
		double rating[] = new double[10];
		int ratingCnt[] = new int[10];
		int save[] = new int[10];

		List<AnalysisDTO> alist = analysisService.getRatingDistributionProcess(member_id);

		for (AnalysisDTO dto : alist) {

			rating[j] = dto.getRating();
			ratingCnt[j] = dto.getRating_cnt();
			j++;
			// dto.get 해서 rating[], ratingCnt[] 각각 배열에 담고
			// 따로 for문 돌리고 증감 0.5 씩(for문 안에는 i++)rating[j] 해서 없으면 배열에 0담고;

		}

		j = 0;

		for (int z = 0; z < 10; z++) {
			if (rating[j] == i) {
				save[z] = ratingCnt[j];
				j++;
			} else {

				save[z] = 0;
			}
			i = i + 0.5;

		}
		
		//추가
		List<SimilarMemberDTO> simMemberList = analysisService.getSimilarMembers(member_id);
//				System.out.println(simPersonList.get(0).getEmail());
		for (int g = 0; g < simMemberList.size(); g++) {
			System.out.println(simMemberList.get(g).getProfile_path());
		}
		
		map.put("simMemberList", simMemberList);
		map.put("ratingDistribution", save);
		map.put("preferredGenre", analysisService.getPreferredGenreProcess(member_id));
		map.put("preferredDirector", analysisService.getPreferredDirectorProcess(member_id));
		map.put("preferredActor", analysisService.getPreferredActorProcess(member_id).subList(0,10));
		return map;
	}

}
