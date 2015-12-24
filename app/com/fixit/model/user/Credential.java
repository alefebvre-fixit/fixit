package com.fixit.model.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import play.data.validation.Constraints.Required;

@Document(collection = "Credential")
public class Credential {

	@Id
	@Required
	private String userId;

	@Required
	public String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
