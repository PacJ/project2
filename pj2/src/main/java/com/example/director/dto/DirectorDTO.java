package com.example.director.dto;

public class DirectorDTO {
	private int director_id;
	private String name;
	private String profile_path;
	
	public DirectorDTO(int director_id, String name, String profile_path) {
		super();
		this.director_id = director_id;
		this.name = name;
		this.profile_path = profile_path;
	}

	public int getDirector_id() {
		return director_id;
	}

	public void setDirector_id(int director_id) {
		this.director_id = director_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfile_path() {
		return profile_path;
	}

	public void setProfile_path(String profile_path) {
		this.profile_path = profile_path;
	}
	
	
}
