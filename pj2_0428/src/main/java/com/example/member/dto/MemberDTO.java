package com.example.member.dto;

import java.sql.Date;

public class MemberDTO {
	private int member_id;
	private String name;
	private String nickname;
	private String email;
	private String password;
	private String profile_path;
	private int gender;
	private int age;
	private int likes_count;
	private int visibility;
	private int grade;
	private Date regDate;
	private String authRole;
	
	public MemberDTO() {
	
	}

	public MemberDTO(int member_id, String name, String nickname, String email, String password, String profile_path,
			int gender, int age, int likes_count, int visibility, int grade, Date regDate, String authRole) {
		super();
		this.member_id = member_id;
		this.name = name;
		this.nickname = nickname;
		this.email = email;
		this.password = password;
		this.profile_path = profile_path;
		this.gender = gender;
		this.age = age;
		this.likes_count = likes_count;
		this.visibility = visibility;
		this.grade = grade;
		this.regDate = regDate;
		this.authRole = authRole;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getProfile_path() {
		return profile_path;
	}

	public void setProfile_path(String profile_path) {
		this.profile_path = profile_path;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getLikes_count() {
		return likes_count;
	}

	public void setLikes_count(int likes_count) {
		this.likes_count = likes_count;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getAuthRole() {
		return authRole;
	}

	public void setAuthRole(String authRole) {
		this.authRole = authRole;
	}

	//매치가 되면 true, 매치가 되지 않으면 false
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}

	public void changePassword(String oldPassword, String newPassword) {
		if (!password.equals(oldPassword))
			throw new com.example.common.exception.WrongEmailPasswordException();
		this.password = newPassword;
	}
	
}
