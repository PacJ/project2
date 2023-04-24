package com.example.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.list.dto.ListDTO;
import com.example.member.dao.MemberDAO;
import com.example.member.dto.MemberDTO;

@Service
public class MemberServiceImp implements MemberService{

	@Autowired
	private MemberDAO memberDao;

	public MemberServiceImp() {
	}
	
	public void setMemberDao(MemberDAO memberDao) {
		this.memberDao = memberDao;
	}

	@Override
	public List<ListDTO> randomService() {
		// TODO Auto-generated method stub
		List<ListDTO> memberList=memberDao.printRandom();
		return memberList;
	}
	@Override
	public void reviewService(String comment) {
		// TODO Auto-generated method stub
		
	}
	
}
