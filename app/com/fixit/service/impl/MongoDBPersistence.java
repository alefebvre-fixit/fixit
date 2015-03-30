package com.fixit.service.impl;

import org.mongojack.JacksonDBCollection;

import play.modules.mongojack.MongoDB;

import com.fixit.model.Contribution;
import com.fixit.model.Favorite;
import com.fixit.model.Project;
import com.fixit.model.User;
import com.fixit.model.notification.Notification;
import com.fixit.model.project.ProjectComment;

public class MongoDBPersistence {

	private static final String CONTRIBUTIONS_COLLECTION = "Contributions";
	private static final String FAVORITES_COLLECTION = "Favorites";
	private static final String USER_COLLECTION = "User";
	private static final String PROJECT_COLLECTION = "Project";
	private static final String NOTIFICATION_COLLECTION = "Notification";
	private static final String COMMENTS_COLLECTION = "comments";

	public static final int MAX_OBJECT = 1000;

	private static JacksonDBCollection<Project, String> projects = MongoDB
			.getCollection(PROJECT_COLLECTION, Project.class, String.class);

	public static final JacksonDBCollection<Project, String> getProjectCollection() {
		return projects;
	}

	private static JacksonDBCollection<User, String> users = MongoDB
			.getCollection(USER_COLLECTION, User.class, String.class);

	public static final JacksonDBCollection<User, String> getUserCollection() {
		return users;
	}

	private static JacksonDBCollection<Favorite, String> favorites = MongoDB
			.getCollection(FAVORITES_COLLECTION, Favorite.class, String.class);

	public static final JacksonDBCollection<Favorite, String> getFavoritesCollection() {
		return favorites;
	}

	private static JacksonDBCollection<Contribution, String> contributions = MongoDB
			.getCollection(CONTRIBUTIONS_COLLECTION, Contribution.class,
					String.class);

	public static final JacksonDBCollection<Contribution, String> getContributionsCollection() {
		return contributions;
	}

	private static JacksonDBCollection<Notification, String> notifications = MongoDB
			.getCollection(NOTIFICATION_COLLECTION, Notification.class,
					String.class);

	public static final JacksonDBCollection<Notification, String> getNotificationsCollection() {
		return notifications;
	}

	private static JacksonDBCollection<ProjectComment, String> comments = MongoDB
			.getCollection(COMMENTS_COLLECTION, ProjectComment.class,
					String.class);

	public static final JacksonDBCollection<ProjectComment, String> getCommentsCollection() {
		return comments;
	}
	
}
