package com.example.security.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.admin.dto.AdminDTO;
import com.example.login.dao.LoginDAO;
import com.example.member.dao.MemberDAO;
import com.example.member.dto.MemberDTO;

@Service
public class PrincipalDetailesService implements UserDetailsService {
	
	@Autowired
	private LoginDAO loginDAO;
	
	public PrincipalDetailesService() {
		
	}
	
	//1. AuthenticationProvider에서 loadUserByname(String username)을 호출한다.
	//2. loadUserByname(String username)에서는 DB에서 username에 해당하는 데이터를 검색해서 UserDetails에 담아서
	//3. AuthenticationProvider에서 UserDetails 받아서 Authentication에 저장을 함으로써 결국 Security Session에 
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		if (email.startsWith("admi!$")) {
			int a = email.indexOf('$');
			String real_email = email.substring(a + 1);
			System.out.println("관리자");
			AdminDTO memberEntity = loginDAO.loginAdmin(real_email);
			if (memberEntity == null) {
				throw new UsernameNotFoundException(real_email);
			}
			return new PrincipalDetails(memberEntity);
			// admin account 일 경우 처리
		} else if (email.startsWith("user!$")) {
			int a = email.indexOf('$');
			String real_email = email.substring(a + 1);
			System.out.println("사용자");
			MemberDTO memberEntity = loginDAO.loginUser(real_email);
			if (memberEntity == null) {
				throw new UsernameNotFoundException(real_email);
			}
			return new PrincipalDetails(memberEntity);
		}

		throw new UsernameNotFoundException(email);
	}

}//end class
