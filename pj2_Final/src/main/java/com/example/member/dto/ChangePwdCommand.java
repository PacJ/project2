package com.example.member.dto;

//기존 비밀번호 > 새로운 비밀번호 변경
public class ChangePwdCommand {

	private String currentPassword;
	private String newPassword;
	
	public ChangePwdCommand() {
		
	}
	
	public String getCurrentPassword() {
		return currentPassword;
	}
	
	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}
	
	public String getNewPassword() {
		return newPassword;
	}
	
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
}//end class
