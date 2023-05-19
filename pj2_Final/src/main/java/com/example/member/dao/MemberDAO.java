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
	public MemberDTO selectById(int member_id);
	public void insertMemGenre(MemberGenreDTO dto);
	public int[] selectMemGenre(int member_id);
	public void deleteMemGenre(int member_id);
	
	public void updateProfileImg(MemberDTO dto);
	public void updateMember(MemberDTO dto);
	public void deleteMember(MemberDTO dto);
	public void updateByPass(MemberDTO dto);
	public int idcheck(String email);
	public String getProfileImg(int member_id);
	
	public List<ListDTO> getWishList(int member_id);
	public List<ListDTO> getRatingList(int member_id);
}
