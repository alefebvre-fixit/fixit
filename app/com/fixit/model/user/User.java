package com.fixit.model.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import play.data.validation.Constraints.Required;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "User")
public class User {

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email
				+ ", password=" + password + "]";
	}

	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Required
	private String username;

	@Required
	private String email;

	@Required
	public String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private Profile profile = new Profile();

	public User() {
	}

	public User(SignUp signup) {
		this.email = signup.getEmail();
		this.username = signup.getUsername();
		this.profile.setName(signup.getName());
		this.password = signup.getPassword();
	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}


	public static final String ANTOINE = "antoinelefebvre";
	public static final String PAUL_SMITH = "paulsmith";

	public static Map<String, User> all() {
		Map<String, User> result = new HashMap<String, User>();

		result.put(ANTOINE, createUser(ANTOINE));
		result.put(PAUL_SMITH, createUser(PAUL_SMITH));

		return result;
	}

	public static User createUser(String username) {
		User result = null;
		if (ANTOINE.equals(username)) {
			result = new User("antoinelefebvre@gmail.com", "password");
			result.username = ANTOINE;
			result.getProfile().setBiography("This is my bio");
			result.getProfile().setWebsite("www.mywebsite.com");
			result.getProfile().setCity("San Francisco");
			result.getProfile().setCountry("United States");
			result.getProfile().setName("Antoine Lefebvre");
		} else if (PAUL_SMITH.equals(username)) {
			result = new User("paulsmith@gmail.com", "password");
			result.username = PAUL_SMITH;
			result.getProfile().setBiography("This is paul bio");
			result.getProfile().setWebsite("www.paulwebsite.com");
			result.getProfile().setCity("Paris");
			result.getProfile().setCountry("France");
			result.getProfile().setName("Paul Smith");
		}
		return result;
	}

}
