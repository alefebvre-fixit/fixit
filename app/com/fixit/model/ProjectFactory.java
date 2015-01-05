package com.fixit.model;

public class ProjectFactory {

	public static final Project createProject(User user) {
		Project result = new Project();
		result.setUsername(user.username);
		result.setCity(user.getProfile().getCity());
		result.setCountry(user.getProfile().getCountry());

		return result;
	}

}
