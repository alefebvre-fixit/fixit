package com.fixit.service.impl;

import org.mongojack.JacksonDBCollection;

import play.modules.mongojack.MongoDB;

import com.fixit.model.Contribution;
import com.fixit.model.Favorite;
import com.fixit.model.Project;
import com.fixit.model.TestProject;
import com.fixit.model.User;

public class MongoDBPersistence {

	public static final int MAX_OBJECT = 1000;
	
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
	
	private static JacksonDBCollection<Favorite, String> favorites = MongoDB
			.getCollection("Favorites", Favorite.class, String.class);

	public static final JacksonDBCollection<Favorite, String> getFavoritesCollection() {
		return favorites;
	}
	
	
	private static JacksonDBCollection<Contribution, String> contributions = MongoDB
			.getCollection("Contributions", Contribution.class, String.class);

	public static final JacksonDBCollection<Contribution, String> getContributionsCollection() {
		return contributions;
	}

	
	private static JacksonDBCollection<TestProject, String> testProjects = MongoDB
			.getCollection("TestProject", TestProject.class, String.class);

	public static final JacksonDBCollection<TestProject, String> getTestProjectCollection() {
		return testProjects;
	}
	
}
