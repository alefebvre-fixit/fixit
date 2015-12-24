package com.fixit.model.user.impl;

import play.data.validation.Constraints.Required;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fixit.model.user.SignIn;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookSignIn extends SignIn{

	@Required
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
