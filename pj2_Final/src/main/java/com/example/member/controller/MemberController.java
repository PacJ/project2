package com.example.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.member.dto.AuthInfo;
import com.example.member.dto.MemberDTO;
import com.example.member.dto.MemberGenreDTO;
import com.example.member.service.MemberService;
import com.example.common.file.FileUpload;
import com.example.list.dto.ListDTO;


@CrossOrigin("*")
@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder encodePassword;
	
	// 회원가입 처리
	// @RequestBody첨부파일이 없는 경우 붙이고 있는 경우에는 붙이지 않는다. (json으로 내보내므로 필요)
	@PostMapping("/register")
	public String addMember(@RequestBody MemberDTO memberDTO) {
		System.out.println("요청");
		System.out.println(memberDTO.getEmail());
		System.out.println(memberDTO.getVisibility());
		
		if(memberDTO.getGender() == 0) {
			memberDTO.setGender(1);
		} //리액트에서 디폴트값으로 gender값을 0을 보냈을때 강제로 gender값을 1로 변경해서 db저장함
		
		memberDTO.setPassword(encodePassword.encode(memberDTO.getPassword()));

		AuthInfo authInfo = memberService.addMemberProcess(memberDTO);
		return null;
	}
	
	@PostMapping("/emailcheck")
	public int checkEmail(@RequestBody Map<String, String> requestBody) {		
		String a = requestBody.get("email");
		int count = memberService.idcheckprocess(a);
		
	    return count;
	}
	
	// 회원정보 가져오기
	@GetMapping("/member/editinfo/{email}")
	public MemberDTO getMember(@PathVariable("email") String email) {
		return memberService.updateMemberProcess(email);
	}

	// 회원정보 수정 처리
	@PostMapping("/profile/update")
	public void updateMember(@RequestBody MemberDTO memberDTO, HttpServletRequest request) {
		System.out.println(memberDTO.getPassword());
		memberDTO.setPassword(encodePassword.encode(memberDTO.getPassword()));
		memberService.updateMemberProcess(memberDTO);
	}
	
	@PostMapping("/profile/delete")
	public void deleteMember(@RequestBody MemberDTO memberDTO, HttpServletRequest request) {
		memberService.deleteMemberProcess(memberDTO);
	}
	
	@PostMapping("/profile/imgUpdate")
	public void updateprofileImg(@RequestParam("file") MultipartFile file, @RequestParam("email") String email, HttpServletRequest request) {
		UUID uuid = FileUpload.saveCopyFile(file, FileUpload.urlPath(request));
	    MemberDTO member = memberService.selectByEmailProcess(email);
	    member.setProfile_path(uuid + "_" + file.getOriginalFilename());
	    System.out.println(member.getProfile_path());
	    memberService.updateProfileImgProcess(member);
	};
	
	@GetMapping("/profile/getProfileImg")
	public String getProfileImg(@RequestParam("email") String email) {
		MemberDTO member = memberService.selectByEmailProcess(email);
		return memberService.getProfileImgProcess(member.getMember_id());
	}
	
	// 회원가입 > 장르선택
	@PostMapping("/genreselect")
	public void addMemberGenre(@RequestBody MemberGenreDTO memberGenreDTO) {

		int memberId = memberGenreDTO.getMemberId();

		memberService.deleteMemGenreProcess(memberId);

		List<Integer> selectedGenre = memberGenreDTO.getSelectedGenre();

		MemberGenreDTO dto = new MemberGenreDTO();

		for (int tagValue : selectedGenre) {
			dto.setMemberId(memberId);
			dto.setGenreId(tagValue);
			memberService.insertMemGenreProcess(dto);
		}

	}

	@GetMapping("/genreselect/{member_id}")
	public int[] getMemberGenre(@PathVariable int member_id) {

//			System.out.println("실행행행행행행행행행행");
		int[] memGenre = memberService.selectMemGenreProcess(member_id);

		// 가져왔는지 확인
		for (int genre : memGenre) {
//				System.out.println("장르다"+ genre);

		}

		return memGenre;
	}

	
	//프로필 영화리스트 가져오기
	@GetMapping("/profile/{member_id}")
	public Map<String, Object> getProfileListExecute(@PathVariable("member_id") int member_id) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberInfo", memberService.selectByIdProcess(member_id));
		map.put("wishList", memberService.getWishListProcess(member_id));
		map.put("ratingList", memberService.getRatingListProcess(member_id));
		return map;
	}

	
}
