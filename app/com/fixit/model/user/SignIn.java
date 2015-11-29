package com.fixit.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import play.data.validation.Constraints.Required;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SignIn {

	private static final long serialVersionUID = 1L;

	@Required
	private String password;

	@Required
	private String username;

	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
