package com.fixit.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import play.Logger;

import com.fixit.model.Contribution;
import com.fixit.model.Project;
import com.fixit.service.ProjectService;

public class MongoProjectService extends BaseProjectService implements
		ProjectService {

	private static final String USER_NAME = "username";

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
	public List<Project> loadByOwner(String username) {
		Logger.debug("loadByOwner(String owner) owner=" + username);

		List<Project> result = getCollection().find().is(USER_NAME, username)
				.toArray();

		return result;
	}

	private JacksonDBCollection<Project, String> getCollection() {
		return MongoDBPersistence.getProjectCollection();
	}

	@Override
	public int countProjectsByOwner(String username) {
		//return getCollection().find().is(USER_NAME, username).count();
		
		int result =  getCollection().find().count();
		Logger.debug("countProjectsByOwner(String owner) owner=" + username + "result = " + result);
		return result;
	}

	@Override
	public int countContributionsByOwner(String username) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Project> loadByOwner(String username, int offset, int length) {
		return getCollection().find().is(USER_NAME, username).skip(offset).limit(length).toArray();
	}

	@Override
	public List<Contribution> loadContributions(String username, int offset,
			int length) {
		// TODO Auto-generated method stub
		return new ArrayList<Contribution>();
	}
}
