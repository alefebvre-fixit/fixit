package com.fixit.service.impl;

import org.mongojack.JacksonDBCollection;

import play.modules.mongojack.MongoDB;

import com.fixit.model.Project;

public class MongoDBPersistence {

	private static JacksonDBCollection<Project, String> projects = MongoDB
			.getCollection("Project", Project.class, String.class);

	public static final JacksonDBCollection<Project, String> getProjectCollection() {
		return projects;
	}

}
