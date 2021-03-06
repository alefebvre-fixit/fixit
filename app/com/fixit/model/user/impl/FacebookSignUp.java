package com.fixit.model.user.impl;

import com.fixit.model.user.SignUp;
import com.restfb.types.User;

public class FacebookSignUp extends SignUp {

	private String faceBookId = null;

	public FacebookSignUp(User facebook) {

		setUsername(facebook.getUsername());
		getProfile().setName(
				facebook.getFirstName() + " " + facebook.getLastName());
		setEmail(facebook.getEmail());
		setFaceBookId(facebook.getId());
		getProfile().setBiography(facebook.getBio());
		getProfile().setWebsite(facebook.getWebsite());
		//getProfile().getPicture().setUrl(facebook.getPicture().getUrl());
	}

	public String getFaceBookId() {
		return faceBookId;
	}

	public void setFaceBookId(String faceBookId) {
		this.faceBookId = faceBookId;
	}

	@Override
	public String toString() {
		return "FacebookSignUp []";
	}

}
