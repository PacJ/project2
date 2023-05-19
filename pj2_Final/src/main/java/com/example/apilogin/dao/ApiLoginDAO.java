package com.example.apilogin.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.member.dto.MemberDTO;

@Mapper
@Repository
public interface ApiLoginDAO {
	public void insertKakaoMember(MemberDTO dto);
	public int countKakaoMember(String email);
	public MemberDTO selectKakaoMember(String email);
	
	
	public void insertNaverMember(MemberDTO dto);
	public int countNaverMember(String email);
	public MemberDTO selectNaverMember(String email);
	public int checkcount(String member_id);
}
