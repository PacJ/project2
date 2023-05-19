package com.example.login.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.admin.dto.AdminDTO;
import com.example.member.dto.MemberDTO;

@Mapper
@Repository
public interface LoginDAO {
	public AdminDTO loginAdmin(String email);
	public MemberDTO loginUser(String email);
}
