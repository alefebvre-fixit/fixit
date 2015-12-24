package com.fixit.model.user;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import play.data.validation.Constraints.Required;

import com.fixit.model.user.impl.EmailSignUp;
import com.fixit.model.user.impl.FacebookSignUp;

@Document(collection = "User")
public class YaUser {

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

	public YaUser() {
	}

	public static YaUser create(SignUp signup){
		if (signup instanceof EmailSignUp){
			return new YaUser((EmailSignUp) signup);
		} else if (signup instanceof FacebookSignUp){
			return new YaUser((FacebookSignUp) signup);
		}
		return null;
	}

	public YaUser(EmailSignUp signup) {
		this.email = signup.getEmail();
		this.username = signup.getUsername();
		this.profile.setName(signup.getProfile().getName());
		this.password = signup.getPassword();
	}

	public YaUser(FacebookSignUp signup) {
		this.email = signup.getEmail();
		this.username = signup.getUsername();
		this.profile = signup.getProfile();
	}

	public YaUser(String email, String password) {
		this.email = email;
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
