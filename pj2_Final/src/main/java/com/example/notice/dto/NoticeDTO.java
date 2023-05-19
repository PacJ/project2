package com.example.notice.dto;

import java.sql.Date;

public class NoticeDTO {
	private int notice_id;
	private int admin_id;
	private String title;
	private String content;
	private String upload;
	private Date date;
	
	public NoticeDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public NoticeDTO(int admin_id, String title, String content, String upload) {
		super();
		this.admin_id = admin_id;
		this.title = title;
		this.content = content;
		this.upload = upload;
	}

	public NoticeDTO(int notice_id, int admin_id, String title, String content, String upload, Date date) {
		super();
		this.notice_id = notice_id;
		this.admin_id = admin_id;
		this.title = title;
		this.content = content;
		this.upload = upload;
		this.date = date;
	}

	public int getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(int notice_id) {
		this.notice_id = notice_id;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUpload() {
		return upload;
	}
	public void setUpload(String upload) {
		this.upload = upload;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
