package com.example.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.member.dto.MemberDTO;

public class PrincipalDetails implements UserDetails {

	private MemberDTO memberDTO;

	public PrincipalDetails() {

	}

	public PrincipalDetails(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}
	
	public MemberDTO getMemberDTO() {
		return memberDTO;
	}

	//권한 목록을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collect = new ArrayList<GrantedAuthority>();
		collect.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return memberDTO.getAuthRole();
			}
		});
		return collect;
	}

	//비밀번호 리턴
	@Override
	public String getPassword() {
		return memberDTO.getPassword();
	}

	//회원이름 리턴
	@Override
	public String getUsername() {
		return memberDTO.getEmail();
	}

	//계정만료여부 리턴- true(만료안됨)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//계정의 잠김여부 리턴 - true(잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//비밀번호의 잠김여부 리턴 - true(잠기지 않음)
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//계정의 활성화 여부 리턴 - true(활성화됨)
	@Override
	public boolean isEnabled() {
		return true;
	}

}//end class
