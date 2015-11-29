package com.fixit.model;

import com.fixit.model.user.User;

public class ProjectFactory {

	public static final Project createProject(User user) {
		Project result = new Project();
		result.setUsername(user.getUsername());
		result.setCity(user.getProfile().getCity());
		result.setCountry(user.getProfile().getCountry());

		return result;
	}

}
