package com.example.notice.service;

import java.util.List;

import com.example.notice.dto.NoticeDTO;

public interface NoticeService {
	public void updateNoticeProcess(NoticeDTO dto);
	public NoticeDTO selectByTitleProcess(String title);
	public List<NoticeDTO> selectAllProcess();
	public NoticeDTO selectByNoticeIdProcess(String notice_id);
	public void editNoticeProcess(NoticeDTO dto);
	public void deleteNoticeProcess(String notice_id);
	public void delimgProcess(String notice_id);
}	
