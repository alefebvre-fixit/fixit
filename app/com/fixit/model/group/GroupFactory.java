package com.fixit.model.group;

import com.fixit.model.user.User;

public class GroupFactory {

	public static final Group createGroup(User user) {
		Group result = new Group();
		result.setUsername(user.getUsername());
		result.setCity(user.getProfile().getCity());
		result.setCountry(user.getProfile().getCountry());

		return result;
	}

}
