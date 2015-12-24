package com.fixit.model;

import com.fixit.model.user.YaUser;

public class ProjectFactory {

	public static final Project createProject(YaUser user) {
		Project result = new Project();
		result.setUsername(user.getUsername());
		result.setCity(user.getProfile().getCity());
		result.setCountry(user.getProfile().getCountry());

		return result;
	}

}
