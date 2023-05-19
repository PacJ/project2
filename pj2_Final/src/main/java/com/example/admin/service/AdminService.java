package com.example.admin.service;

import java.util.List;

import com.example.admin.dto.AdminDTO;

import com.example.review.dto.ReviewDTO;
import com.example.review.dto.ReviewInfoDTO;

public interface AdminService {

	
	public int addAdminProcess(AdminDTO dto);
//	public AuthInfo loginProcess(AdminDTO dto);
//	
//	public MembersDTO updateMemberProcess(String memberEmail);
//	public AuthInfo updateMemberProcess(MembersDTO dto);
//	public void updatePassProcess(String memberEmail, ChangePwdCommand changePwd);
	public int idcheckservcie(String email);
	public int updateNameProcess(AdminDTO dto);
	public List<ReviewDTO> editSpoilerProcess();
	public List<ReviewDTO> editReportProcess();
	public void blurSpoilerprocess(ReviewInfoDTO blurspoiler);
	public void returnSpoilerprocess(ReviewInfoDTO returnspoiler);
	public void deleteReviewprocess(ReviewInfoDTO deletereview);
}
