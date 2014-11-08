package com.fixit.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.data.validation.Constraints.Required;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fixit.model.account.SignUp;

@Entity
public class User {

	private static final long serialVersionUID = 1L;

	@Id
	@Required
	public String username;

	@Required
	public String email;

	@Required
	@JsonIgnore
	public String password;

    private Profile profile= new Profile();

	public User() {
	}
	
	public User(SignUp signup) {
		this.email = signup.getEmail();
		this.username = signup.getUsername();
		this.profile.setName(signup.getName());
	}

	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	@JsonIgnore
	public AccountSummary getSummary() {
		return new AccountSummary(this);
	}

    public Profile getProfile(){
        return profile;
    }

    public void setProfile(Profile profile){
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
	
	public static User createUser(String username){
		User result = null;
		if (ANTOINE.equals(username)){
			result = new User("antoinelefebvre@gmail.com", "password");
			result.username = ANTOINE;
			result.getProfile().setBiography("This is my bio");
			result.getProfile().setWebsite("www.mywebsite.com");
			result.getProfile().setCity("San Francisco");
			result.getProfile().setCountry("United States");
			result.getProfile().setName("Antoine Lefebvre");
		} else if (PAUL_SMITH.equals(username)){
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
