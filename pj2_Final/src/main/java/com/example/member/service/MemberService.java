package com.example.member.service;

import java.util.List;

import com.example.list.dto.ListDTO;
import com.example.member.dto.AuthInfo;
import com.example.member.dto.ChangePwdCommand;
import com.example.member.dto.MemberDTO;
import com.example.member.dto.MemberGenreDTO;

public interface MemberService {
	public AuthInfo addMemberProcess(MemberDTO dto);
	public AuthInfo loginProcess(MemberDTO dto); 
	
	public void insertMemGenreProcess(MemberGenreDTO dto);
	
	public MemberDTO updateMemberProcess(String email);
	public AuthInfo updateMemberProcess(MemberDTO dto);
	public void updatePassProcess(String email, ChangePwdCommand changePwd);
	public void deleteMemberProcess(MemberDTO dto);
	
	public void updateProfileImgProcess(MemberDTO dto);
	public MemberDTO selectByEmailProcess(String email);
	public MemberDTO selectByIdProcess(int member_id);
	//public boolean isEmailAlreadyInUse(String email);
	public int idcheckprocess(String email);
	public String getProfileImgProcess(int member_id);
	
	//마이페이지 위시,레이팅 리스트
	public List<ListDTO> getWishListProcess(int member_id);
	public List<ListDTO> getRatingListProcess(int member_id);
	
	public int[] selectMemGenreProcess(int member_id);
	public void deleteMemGenreProcess(int member_id);
}
