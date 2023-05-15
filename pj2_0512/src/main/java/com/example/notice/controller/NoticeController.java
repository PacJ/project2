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
	public String updatefile(@RequestParam("file") MultipartFile file, @RequestParam("title") String title,
			@RequestParam("admin_id") String admin_id, @RequestParam("content") String content, HttpServletRequest request) {
		System.out.println(title);
		System.out.println(admin_id);
		System.out.println(content);
		System.out.println(file);
		UUID uuid = FileUpload.saveCopyFile(file, FileUpload.urlPath(request));
		String upload = uuid + "_" + file.getOriginalFilename();
		System.out.println(FileUpload.urlPath(request));
		NoticeDTO notice = new NoticeDTO(Integer.parseInt(admin_id), title, content, upload);
		System.out.println(uuid);
		System.out.println(title);
		System.out.println(uuid + "_" + file.getOriginalFilename());
		noticeService.updateNoticeProcess(notice);
//	       NoticeDTO notice= noticeService.selectByTitleProcess(title);
//	      
//	       notice.setUpload(uuid+ "_" +file.getOriginalFilename());

		// 업로드처리
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
	public String modifyfile(@RequestParam("file") MultipartFile file, @RequestParam("title") String title,
			@RequestParam("admin_id") String admin_id, @RequestParam("content") String content,
			@RequestParam("notice_id") String notice_id, HttpServletRequest request) {
		System.out.println(title);
		System.out.println(admin_id);
		System.out.println(content);
		System.out.println(file);
		UUID uuid = FileUpload.saveCopyFile(file, FileUpload.urlPath(request));
		String upload = uuid + "_" + file.getOriginalFilename();
		System.out.println(FileUpload.urlPath(request));
		
		NoticeDTO notice = new NoticeDTO(Integer.parseInt(admin_id), title, content, upload);
		notice.setNotice_id(Integer.parseInt(notice_id));
		System.out.println(uuid);
		System.out.println(title);
		System.out.println(uuid + "_" + file.getOriginalFilename());
		noticeService.editNoticeProcess(notice);
//	       NoticeDTO notice= noticeService.selectByTitleProcess(title);
//	      
//	       notice.setUpload(uuid+ "_" +file.getOriginalFilename());

		// 업로드처리   
		System.out.println("notice_id"+notice_id);
		return "1234";
	};
	
	@DeleteMapping("/deletenotice/{notice_id}")
	public String deleteN(@PathVariable String notice_id, HttpServletRequest request) {
		
		System.out.println("deleten");
		System.out.println(notice_id);
		noticeService.deleteNoticeProcess(notice_id);		
		
		return "성공";
		
	}
}
