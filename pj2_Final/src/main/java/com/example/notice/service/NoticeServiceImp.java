package com.example.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.notice.dao.NoticeDAO;
import com.example.notice.dto.NoticeDTO;

@Service
public class NoticeServiceImp implements NoticeService{
	@Autowired
	private NoticeDAO dao;
	
	
	public NoticeServiceImp() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void updateNoticeProcess(NoticeDTO dto) {
		// TODO Auto-generated method stub
		dao.updateNotice(dto);
	}
	@Override
	public NoticeDTO selectByTitleProcess(String title) {
		// TODO Auto-generated method stub
		return dao.selectByTitle(title);
	}
	@Override
	public List<NoticeDTO> selectAllProcess() {
		// TODO Auto-generated method stub
		return dao.selectAll();
	}
	
	@Override
	public NoticeDTO selectByNoticeIdProcess(String notice_id) {
		// TODO Auto-generated method stub
		return dao.selectByNoticeId(notice_id);
	}
	@Override
	public void editNoticeProcess(NoticeDTO dto) {
		// TODO Auto-generated method stub
		dao.editNotice(dto);
	}
	@Override
	public void deleteNoticeProcess(String notice_id) {
		// TODO Auto-generated method stub
		dao.deleteNotice(notice_id);
	}
	@Override
	public void delimgProcess(String notice_id) {
		// TODO Auto-generated method stub
		dao.delimg(notice_id);
	}
	
}
