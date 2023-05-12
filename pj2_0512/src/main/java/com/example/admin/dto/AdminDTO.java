package com.example.admin.dto;

public class AdminDTO {
	private int admin_id;
	private String email;
	private String password;
	private String name;
	private String authRole;
	
	public AdminDTO() {
		// TODO Auto-generated constructor stub
	}

	public AdminDTO(int admin_id, String email, String password, String name, String authRole) {
		super();
		this.admin_id = admin_id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.authRole = authRole;
	}

	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthRole() {
		return authRole;
	}

	public void setAuthRole(String authRole) {
		this.authRole = authRole;
	}	
}
