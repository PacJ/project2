package com.example.notice.controller;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.common.file.FileUpload;
import com.example.member.service.MemberService;
import com.example.notice.dto.NoticeDTO;
import com.example.notice.service.NoticeService;

@CrossOrigin({ "http://localhost:3000" })
@RestController
public class NoticeController {
	@Autowired
	private NoticeService noticeService;

	public NoticeController() {
		// TODO Auto-generated constructor stub
	}

	@PostMapping("/noticefileupdate")
	public String updatefile(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam("title") String title, @RequestParam("admin_id") String admin_id,
			@RequestParam("content") String content, HttpServletRequest request) {
		System.out.println(title);
		System.out.println(admin_id);
		System.out.println(content);
		String upload = "";
		System.out.println(file);

		System.out.println(FileUpload.urlPath(request));
		NoticeDTO notice = new NoticeDTO();

		notice.setAdmin_id(Integer.parseInt(admin_id));
		notice.setTitle(title);
		notice.setContent(content);

		if (file != null && !file.isEmpty()) {
			UUID uuid = FileUpload.saveCopyFile(file, FileUpload.urlPath(request));
			upload = uuid + "_" + file.getOriginalFilename();
			notice.setUpload(upload);
		} else {
			upload = "";
			notice.setUpload(upload);
		}

		noticeService.updateNoticeProcess(notice);
		return "1234";
	};

	@GetMapping("/selectallnotice")
	public List<NoticeDTO> notice() {
		System.out.println("notice");
		return noticeService.selectAllProcess();
	}

	@PostMapping("/getnotice_id")
	public NoticeDTO gni(@RequestBody String notice_id) {
		System.out.println(notice_id);
		NoticeDTO dto = noticeService.selectByNoticeIdProcess(notice_id);
		System.out.println(dto.getContent());

		return dto;
	}

	@PostMapping("/modifynotice")
	public String modifyfile(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam("title") String title, @RequestParam("admin_id") String admin_id,
			@RequestParam("content") String content, @RequestParam("notice_id") String notice_id,
			HttpServletRequest request) {
		System.out.println(title);
		System.out.println(admin_id);
		System.out.println(content);

		String upload = "";
		NoticeDTO original = noticeService.selectByNoticeIdProcess(notice_id);

		NoticeDTO notice = new NoticeDTO();
		notice.setNotice_id(Integer.parseInt(notice_id));
		notice.setAdmin_id(Integer.parseInt(admin_id));
		notice.setTitle(title);
		notice.setContent(content);

		if (file != null && !file.isEmpty()) {
			// notice id 로 가져와서 파일명이 같지 않을때만 아래 3줄 실행
			System.out.println("실행");
			System.out.println(file);
			System.out.println(file.getName());
			System.out.println(file.getOriginalFilename());
			UUID uuid = FileUpload.saveCopyFile(file, FileUpload.urlPath(request));
			upload = uuid + "_" + file.getOriginalFilename();
			notice.setUpload(upload);
		} else {
			System.out.println("file");
			System.out.println("file" + file);
			upload = "";
		}
		
		noticeService.editNoticeProcess(notice);

		return "1234";
	};
	
	@DeleteMapping("/deletenotice/{notice_id}")
	public String deleteN(@PathVariable String notice_id, HttpServletRequest request) {
		System.out.println("deleten");
		System.out.println(notice_id);
		noticeService.deleteNoticeProcess(notice_id);		
		
		return "성공";
	}
	
	@PostMapping("/delimg")
	public String delfile(@RequestBody String notice_id) {
		System.out.println(notice_id);
		noticeService.delimgProcess(notice_id);
		return null;
	}
}
