package com.example.apilogin.service;

import com.example.member.dto.MemberDTO;

public interface ApiLoginService {
	
	
	public void insertKakaoMemberProcess(MemberDTO dto);
	public int countKakaoMemberProcess(String email);
	public MemberDTO selectKakaoMemberProcess(String email);
	public void insertNaverMemberProcess(MemberDTO dto);
	public int countNaverMemberProcess(String email);
	public MemberDTO selectNaverMemberProcess(String email);
	public int checkcountProcess(String member_id);
}
