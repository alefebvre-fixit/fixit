package com.fixit.model.account;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

public class SignUp extends Model {

	private static final long serialVersionUID = 1L;

	@Required
	private String username;

	@Required
	private String name;

	@Required
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

}
