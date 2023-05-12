package com.example.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.common.exception.WrongEmailPasswordException;
import com.example.list.dto.ListDTO;
import com.example.member.dao.MemberDAO;
import com.example.member.dto.AuthInfo;
import com.example.member.dto.ChangePwdCommand;
import com.example.member.dto.MemberDTO;
import com.example.member.dto.MemberGenreDTO;

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
	@Transactional
	public void insertMemGenreProcess(MemberGenreDTO dto) {
		memberDao.insertMemGenre(dto);
	}

	@Override
	@Transactional
	public AuthInfo addMemberProcess(MemberDTO dto) {
		memberDao.insertMember(dto);
		return new AuthInfo(dto.getEmail(), dto.getName(), dto.getPassword(), dto.getAge(), dto.getGender(), dto.getNickname(), dto.getProfile_path());
	}

	@Override
	public AuthInfo loginProcess(MemberDTO dto) {
		MemberDTO member =  memberDao.selectByEmail(dto.getEmail());
		
		if(member == null) {
			//System.out.println("회원이 아닙니다.");
			throw new WrongEmailPasswordException();
		}
		
		if(!member.matchPassword(dto.getPassword())) {
			//System.out.println("비밀번호가 다릅니다.");
			throw new WrongEmailPasswordException();
		}
		return new AuthInfo(dto.getEmail(), dto.getName(), dto.getPassword(), dto.getAge(), dto.getGender(), dto.getNickname(), dto.getProfile_path());
	}

	@Override
	public MemberDTO updateMemberProcess(String email) {
		return memberDao.selectByEmail(email);
	}

	@Override
	public AuthInfo updateMemberProcess(MemberDTO dto) {
		memberDao.updateMember(dto);
		return new AuthInfo(dto.getEmail(), dto.getName(), dto.getPassword(), dto.getAge(), dto.getGender());
	}

	@Override
	public void updatePassProcess(String email, ChangePwdCommand changePwd) {
		MemberDTO member = memberDao.selectByEmail(email);
		if(member == null)
			throw new WrongEmailPasswordException();
		
		member.changePassword(changePwd.getCurrentPassword(), changePwd.getNewPassword());
		memberDao.updateByPass(member);
	}

	@Override
	public MemberDTO selectByEmailProcess(String email) {
		return memberDao.selectByEmail(email);
	}
 
	@Override
	public void updateProfileImgProcess(MemberDTO dto) {
		memberDao.updateProfileImg(dto);
	}
	
	@Override
	public int idcheckprocess(String email) {
		return memberDao.idcheck(email);
	}
	
	@Override
	public String getProfileImgProcess(int member_id) {
		return memberDao.getProfileImg(member_id);
	}

	@Override
	public List<ListDTO> getWishListProcess(int member_id) {
		return memberDao.getWishList(member_id);
	}

	@Override
	public List<ListDTO> getRatingListProcess(int member_id) {
		return memberDao.getRatingList(member_id);
	}
	
	@Override
	public MemberDTO selectByIdProcess(int member_id) {
		return memberDao.selectById(member_id);
	}
	
}
