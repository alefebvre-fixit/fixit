package com.fixit.service.impl;

import org.mongojack.JacksonDBCollection;

import play.modules.mongojack.MongoDB;

import com.fixit.model.Project;
import com.fixit.model.User;

public class MongoDBPersistence {

	private static JacksonDBCollection<Project, String> projects = MongoDB
			.getCollection("Project", Project.class, String.class);

	public static final JacksonDBCollection<Project, String> getProjectCollection() {
		return projects;
	}
	
	private static JacksonDBCollection<User, String> users = MongoDB
			.getCollection("User", User.class, String.class);

	public static final JacksonDBCollection<User, String> getUserCollection() {
		return users;
	}

}
