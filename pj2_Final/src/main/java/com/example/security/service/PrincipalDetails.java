package com.example.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.admin.dto.AdminDTO;
import com.example.member.dto.MemberDTO;

public class PrincipalDetails implements UserDetails {
	private MemberDTO memberDTO;
	private AdminDTO adminDTO;

	public PrincipalDetails() {

	}

	public PrincipalDetails(MemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}
	
	public PrincipalDetails(AdminDTO adminDTO) {
		this.adminDTO = adminDTO;
	}
	
	public Object getDTO() {
		if(memberDTO == null) {
			return adminDTO;
		}else {
			return memberDTO;
		}
	}
	
	public MemberDTO getMemberDTO() {
		return memberDTO;
	}
	
	public AdminDTO getAdminDTO() {
		return adminDTO;
	}

	//권한 목록을 리턴
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> collect = new ArrayList<GrantedAuthority>();

		if (memberDTO != null) {
			collect.add(new GrantedAuthority() {
				@Override
				public String getAuthority() {
					return "사용자";
				}

			});
		} else if (adminDTO != null) {
			collect.add(new GrantedAuthority() {
				@Override
				public String getAuthority() {
					return "관리자";
				}

			});
		}
		return null;
	}

	//비밀번호 리턴
	@Override
	public String getPassword() {
		if (memberDTO != null) {
			return memberDTO.getPassword();
		} else if (adminDTO != null) {
			return adminDTO.getPassword();
		} else {
			return null;
		}
	}

	//회원이름 리턴
	@Override
	public String getUsername() {
		if (memberDTO != null) {
			return memberDTO.getName();
		} else if (adminDTO != null) {
			return adminDTO.getName();
		} else {
			return null;
		}
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
