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
	
	public List<ListDTO> randomService();
}
