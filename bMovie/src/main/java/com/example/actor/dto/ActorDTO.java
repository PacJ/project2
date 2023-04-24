package com.example.actor.dto;

public class ActorDTO {
	private int actor_id;
	private String name;
	private String profile_path;
	
	public ActorDTO(int actor_id, String name, String profile_path) {
		super();
		this.actor_id = actor_id;
		this.name = name;
		this.profile_path = profile_path;
	}

	public int getActor_id() {
		return actor_id;
	}

	public void setActor_id(int actor_id) {
		this.actor_id = actor_id;
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
