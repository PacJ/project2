package com.example.apilogin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.apilogin.dao.ApiLoginDAO;
import com.example.member.dto.MemberDTO;

@Service
public class ApiLoginServiceImp implements ApiLoginService{
	@Autowired
	private ApiLoginDAO dao;
	
	@Override
	public void insertKakaoMemberProcess(MemberDTO dto) {
		// TODO Auto-generated method stub
		dao.insertKakaoMember(dto);
	}
	@Override
	public int countKakaoMemberProcess(String email) {
		// TODO Auto-generated method stub
		return dao.countKakaoMember(email);
	}
	
	@Override
	public MemberDTO selectKakaoMemberProcess(String email) {
		// TODO Auto-generated method stub
		return dao.selectKakaoMember(email);
	}
	
	
	
	@Override
	public void insertNaverMemberProcess(MemberDTO dto) {
		// TODO Auto-generated method stub
		dao.insertNaverMember(dto);
	}
	@Override
	public int countNaverMemberProcess(String email) {
		// TODO Auto-generated method stub
		return dao.countNaverMember(email);
	}
	
	@Override
	public MemberDTO selectNaverMemberProcess(String email) {
		// TODO Auto-generated method stub
		return dao.selectNaverMember(email);
	}
	@Override
	public int checkcountProcess(String member_id) {
		// TODO Auto-generated method stub
		return dao.checkcount(member_id);
	}
	
	
	
	
}
