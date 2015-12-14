package com.fixit.model.group;

import java.util.Date;

import com.fixit.model.user.User;

public class GroupFactory {

	public static final Group createGroup(User user) {
		Group result = new Group();
		
		result.setCreationDate(new Date());
		result.setModificationDate(result.getCreationDate());
		result.setUsername(user.getUsername());
		result.getSponsors().add(user.getUsername());
		result.setCity(user.getProfile().getCity());
		result.setCountry(user.getProfile().getCountry());

		return result;
	}

}
