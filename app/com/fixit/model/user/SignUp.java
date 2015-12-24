package com.fixit.model.user;

import play.data.validation.Constraints.Required;

public abstract class SignUp {

	@Required
	private String username;

	@Required
	private String email;
	
	private Profile profile = new Profile();
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
