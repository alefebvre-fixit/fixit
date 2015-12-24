package com.fixit.model.user.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fixit.model.user.SignIn;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FacebookSignIn extends SignIn {

	private String token;
	private String expiration;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getExpiration() {
		return expiration;
	}

	@Override
	public String toString() {
		return "FacebookSignIn [token=" + token + ", expiration=" + expiration
				+ "]";
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

}
