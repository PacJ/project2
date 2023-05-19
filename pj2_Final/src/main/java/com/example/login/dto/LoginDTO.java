package com.example.login.dto;

public class LoginDTO {
	
	private String email;
	private String password;
	private String authRole;
	
	public LoginDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public LoginDTO(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public LoginDTO(String email, String password, String authRole) {
		super();
		this.email = email;
		this.password = password;
		this.authRole = authRole;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthRole() {
		return authRole;
	}

	public void setAuthRole(String authRole) {
		this.authRole = authRole;
	}
	
	
	
}

