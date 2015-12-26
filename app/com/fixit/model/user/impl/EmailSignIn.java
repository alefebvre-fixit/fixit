package com.fixit.model.user.impl;

import play.data.validation.Constraints.Required;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fixit.model.user.SignIn;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailSignIn extends SignIn{

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

	@Override
	public String toString() {
		return "EmailSignIn [password=" + password + ", username=" + username
				+ "]";
	}

}
