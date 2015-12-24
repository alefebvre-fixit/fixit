package com.fixit.model.user.impl;

import com.fixit.model.user.SignUp;
import com.restfb.types.User;

public class FacebookSignUp extends SignUp {
	
	
	public FacebookSignUp(User facebook){
		
		setUsername(facebook.getUsername());
		getProfile().setName(facebook.getFirstName() + " "  + facebook.getLastName());
		setEmail(facebook.getEmail());		
		getProfile().setBiography(facebook.getBio());
		getProfile().setWebsite(facebook.getWebsite());
		getProfile().getPicture().setUrl(facebook.getPicture().getUrl());
		
	
	}
	

}
