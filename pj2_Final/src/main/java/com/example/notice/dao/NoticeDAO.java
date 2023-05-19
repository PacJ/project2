package com.example.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.notice.dto.NoticeDTO;

@Mapper
@Repository
public interface NoticeDAO {
	public void updateNotice(NoticeDTO dto);
	public NoticeDTO selectByTitle(String title);
	public List<NoticeDTO> selectAll();
	public NoticeDTO selectByNoticeId(String notice_id);
	public void editNotice(NoticeDTO dto);
	public void deleteNotice(String notice_id);
	public void delimg(String notice_id);
}
