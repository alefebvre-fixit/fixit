package com.fixit.model.user.impl;

import play.data.validation.Constraints.Required;

import com.fixit.model.user.SignUp;

public class EmailSignUp extends SignUp {

	@Required
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "EmailSignUp [password=" + password + "]";
	}

}
