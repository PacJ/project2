package com.example.admin.service;

import java.util.List;

//주입된 adminDao 개체에 AdminDAO 인터페이스 및 insertAdmin() 메소드의 실제 구현.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.admin.dao.AdminDAO;
import com.example.admin.dto.AdminDTO;

import com.example.review.dto.ReviewDTO;
import com.example.review.dto.ReviewInfoDTO;



@Service
public class AdminServiceImp implements AdminService {
	
	@Autowired
//	DAO DTO 의존성주입
	private AdminDAO adminDao;

	public AdminServiceImp() {
	}

	public void setAdminDao(AdminDAO adminDao) {
		this.adminDao = adminDao;
	}

	@Override
//	하위 클래스의 메서드가 상위 클래스의 메서드를 재정의하도록 의도
	public int addAdminProcess(AdminDTO dto) {

		return adminDao.insertAdmin(dto);

	}
	@Override
	public int idcheckservcie(String email) {
		// TODO Auto-generated method stub
		return adminDao.idCheck(email);
	}
	@Override
	public int updateNameProcess(AdminDTO dto) {
		// TODO Auto-generated method stub
		return adminDao.updateAdmin(dto);
	}
	@Override
	public List<ReviewDTO> editSpoilerProcess() {
		// TODO Auto-generated method stub
		return adminDao.editSpoiler();
	}
	
	@Override
	public List<ReviewDTO> editReportProcess() {
		// TODO Auto-generated method stub
		return adminDao.editReport();
	}

	@Override
	public void blurSpoilerprocess(ReviewInfoDTO blurspoiler) {
		// TODO Auto-generated method stub
		adminDao.blurSpoiler(blurspoiler);
	}

	@Override
	public void deleteReviewprocess(ReviewInfoDTO deletereview) {
		adminDao.deleteReview(deletereview);
		
	}

	@Override
	public void returnSpoilerprocess(ReviewInfoDTO returnspoiler) {
		// TODO Auto-generated method stub
		adminDao.returnSpoiler(returnspoiler);
		
	}
	
//	
	
//	@Override
//	public AuthInfo loginProcess(AdminDTO dto) {
//		AdminDTO admin = adminDao.selectByEmail(dto.getAdminEmail());
//		if(admin == null) {
//			//System.out.println("Admin does not exist.");
//			throw new WrongEmailPasswordException();
//		}
//		
//		if(!admin.matchPassword(dto.getAdminPass())) {
//			//System.out.println("Wrong password.");
//			throw new WrongEmailPasswordException();
//		}
//		
//		return new AuthInfo(admin.getAdminEmail(), admin.getAdminName(), admin.getAdminPass());
//	}
//
//	@Override
//	public AdminDTO updateAdminProcess(String adminEmail) {
//		return adminDao.selectByEmail(adminEmail);
//	}
//
//	@Override
//	public AuthInfo updateAdminProcess(AdminDTO dto) {
//		adminDao.updateAdmin(dto);
//		AdminDTO admin = adminDao.selectByEmail(dto.getAdminEmail());
//		return new AuthInfo(admin.getAdminEmail(), admin.getAdminName(), admin.getAdminPass());
//	}
//
//	@Override
//	public void updatePassProcess(String adminEmail, ChangePwdCommand changePwd) {
//	     AdminDTO admin = adminDao.selectByEmail(adminEmail); 
//	     if(admin == null)
//	    	 throw new WrongEmailPasswordException();
//	     
//	     admin.changePassword(changePwd.getCurrentPassword(), changePwd.getNewPassword());
//	     adminDao.updateByPass(admin);
//
//	}

}





