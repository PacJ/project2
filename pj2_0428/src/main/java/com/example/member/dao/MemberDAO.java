package com.example.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.list.dto.ListDTO;
import com.example.member.dto.MemberDTO;
import com.example.member.dto.MemberGenreDTO;

@Mapper
@Repository
public interface MemberDAO {
	
	public void insertMember(MemberDTO dto);
	public MemberDTO selectByEmail(String email);
	public void insertMemGenre(MemberGenreDTO dto);
	
	public void updateMember(MemberDTO dto);
	public void updateByPass(MemberDTO dto);
	
	public List<ListDTO> printRandom();
	
}
