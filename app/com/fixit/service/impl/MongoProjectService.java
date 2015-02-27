package com.fixit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import play.Logger;

import com.fixit.model.Contribution;
import com.fixit.model.Favorite;
import com.fixit.model.Project;
import com.fixit.model.TestProject;
import com.fixit.service.ProjectService;

public class MongoProjectService extends BaseProjectService implements
		ProjectService {

	private static final String USER_NAME = "username";
	private static final String PROJECT_ID = "projectId";
	private static final String CARD_ID = "cardId";
	private static final String CONTRIBUTION_ID = "contributionId";
	private static final String CONTRIBUTOR = "contributor";

	private JacksonDBCollection<Project, String> getCollection() {
		return MongoDBPersistence.getProjectCollection();
	}

	private JacksonDBCollection<Favorite, String> getFavoritesCollection() {
		return MongoDBPersistence.getFavoritesCollection();
	}

	private JacksonDBCollection<Contribution, String> getContributionsCollection() {
		return MongoDBPersistence.getContributionsCollection();
	}

	private JacksonDBCollection<TestProject, String> getTestProjectCollection() {
		return MongoDBPersistence.getTestProjectCollection();
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
	public Project load(String id) {
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
	public int countContributionsByOwner(String username) {
		return getContributionsCollection().find().is(CONTRIBUTOR, username)
				.count();
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
	public List<Contribution> getUserContributions(String username, int offset,
			int length) {
		DBCursor<Contribution> cursor = getContributionsCollection().find().is(
				CONTRIBUTOR, username);
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
		// TODO Find a better way
		List<Favorite> favorites = getFavoritesCollection().find()
				.is(USER_NAME, username).is(PROJECT_ID, projectId).toArray();
		if (favorites != null) {
			for (Favorite favorite : favorites) {
				getFavoritesCollection().removeById(favorite.getId());
			}
		}
	}

	@Override
	public List<String> favorites(String username) {
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
	public List<Contribution> getProjectContributions(String username,
			String projectId) {
		return getContributionsCollection().find().is(CONTRIBUTOR, username)
				.is(PROJECT_ID, projectId).toArray();
	}

	@Override
	public List<Contribution> getCardContributions(String cardId) {
		return getContributionsCollection().find().is(CARD_ID, cardId)
				.toArray();
	}

	@Override
	public Contribution getContribution(String contributionId) {
		return getContributionsCollection().findOneById(contributionId);
	}

	@Override
	public Contribution saveContribution(Contribution contribution) {
		WriteResult<Contribution, String> result = null;
		if (contribution.getId() == null) {
			result = getContributionsCollection().insert(contribution);
			contribution.setId(result.getSavedId());

		} else {
			result = getContributionsCollection().updateById(
					contribution.getId(), contribution);
		}

		return contribution;
	}

	@Override
	public void deleteContribution(String contributionId) {
		getContributionsCollection().removeById(contributionId);
	}

	@Override
	public List<Contribution> getProjectContributions(String projectId) {
		return getContributionsCollection().find().is(PROJECT_ID, projectId)
				.toArray();

	}

}
