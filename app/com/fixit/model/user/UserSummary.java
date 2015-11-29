package com.fixit.model.user;


public class UserSummary {

	private Profile profile;
	private String username;

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static UserSummary create(User user) {
		UserSummary result = new UserSummary();
		result.setProfile(user.getProfile());
		result.setUsername(user.getUsername());
		return result;
	}

}
