package com.fixit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import play.Logger;

import com.fixit.model.Favorite;
import com.fixit.model.Project;
import com.fixit.model.account.UserCard;
import com.fixit.service.ContributionService;
import com.fixit.service.ProjectService;
import com.fixit.service.UserService;

public class MongoProjectService extends BaseProjectService implements
		ProjectService {

	public static final String USER_NAME = "username";
	public static final String PROJECT_ID = "projectId";
	public static final String CARD_ID = "cardId";

	private JacksonDBCollection<Project, String> getCollection() {
		return MongoDBPersistence.getProjectCollection();
	}

	private JacksonDBCollection<Favorite, String> getFavoritesCollection() {
		return MongoDBPersistence.getFavoritesCollection();
	}
	
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public MongoProjectService(UserService userService){
		this.userService = userService;
	}
	

	@Override
	public List<Project> getAll() {
		Logger.debug("MongoProjectService.getAll()");
		return getCollection().find().toArray();
	}

	@Override
	public void delete(String id) {
		Logger.debug("MongoProjectService.delete(String id) id=" + id);

		getCollection().removeById(id);
	}

	@Override
	public String create(Project project) {
		Project result = save(project);
		return result.getId();
	}

	@Override
	public Project save(Project project) {
		WriteResult<Project, String> result = null;
		assignCardIds(project);
		project.incrementVersion();
		if (project.getId() == null) {
			Logger.debug("MongoProjectService.save.insert()");
			result = getCollection().insert(project);
			project.setId(result.getSavedId());
		} else {
			Logger.debug("MongoProjectService.save.updateById(String id) id="
					+ project.id);

			result = getCollection().updateById(project.id, project);
		}

		return project;
	}

	@Override
	public Project getProject(String id) {
		Logger.debug("MongoProjectService.load(String id) id=" + id);
		Project result = getCollection().findOneById(id);
		return result;
	}

	@Override
	public int countProjectsByOwner(String username) {
		int result = getCollection().find().count();
		Logger.debug("countProjectsByOwner(String owner) owner=" + username
				+ "result = " + result);
		return result;
	}

	@Override
	public List<Project> getUserProjects(String username, int offset, int length) {
		DBCursor<Project> cursor = getCollection().find().is(USER_NAME,
				username);
		if (offset > 0) {
			cursor.skip(offset);
		}
		if (length > 0) {
			cursor.limit(length);
		}
		return cursor.toArray(MongoDBPersistence.MAX_OBJECT);
	}

	@Override
	public void follow(String username, String projectId) {
		Favorite favorite = new Favorite();
		favorite.setCreationDate(new Date());
		favorite.setUsername(username);
		favorite.setProjectId(projectId);

		int count = getFavoritesCollection().find().is(USER_NAME, username)
				.is(PROJECT_ID, projectId).count();
		if (count <= 0) {
			getFavoritesCollection().insert(favorite);
		}
	}

	@Override
	public void unfollow(String username, String projectId) {
		List<Favorite> favorites = getFavoritesCollection().find()
				.is(USER_NAME, username).is(PROJECT_ID, projectId).toArray();
		if (favorites != null) {
			for (Favorite favorite : favorites) {
				getFavoritesCollection().removeById(favorite.getId());
			}
		}
	}

	@Override
	public List<String> projectFollowed(String username) {
		List<String> result = new ArrayList<String>();

		List<Favorite> favorites = getFavoritesCollection().find()
				.is(USER_NAME, username).toArray();
		if (favorites != null) {
			for (Favorite favorite : favorites) {
				result.add(favorite.getProjectId());
			}
		}
		return result;
	}

	@Override
	public List<UserCard> projectFollowers(String projectId) {
		// TODO Improve implementation by loading only the username
		
		List<UserCard> result = new ArrayList<UserCard>();

		List<Favorite> favorites = getFavoritesCollection().find()
				.is(PROJECT_ID, projectId).toArray();
		if (favorites != null) {
			for (Favorite favorite : favorites) {
				UserCard userCard = userService.getUserCard(favorite.getUsername());
				if (userCard != null){
					result.add(userCard);
				}
			}
		}
		
		return result;
	}

	@Override
	public String getProjectOwner(String projectId) {
		// TODO Improve implementation by loading only the username
		
		String result = null;
		
		Project project = getProject(projectId);
		if (project != null){
			result = project.getUsername();
		}
		
		return result;
	}

	@Override
	public int projectFollowersSize(String projectId) {
		return getFavoritesCollection().find()
				.is(PROJECT_ID, projectId).count();
	}



}
