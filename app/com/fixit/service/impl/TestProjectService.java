package com.fixit.service.impl;

import java.util.ArrayList;
import java.util.List;

import play.Logger;

import com.fixit.model.Contribution;
import com.fixit.model.Favorite;
import com.fixit.model.Project;
import com.fixit.service.ProjectService;

public class TestProjectService extends BaseProjectService implements ProjectService {

	@Override
	public List<Project> getAll() {
		return all();
	}

	@Override
	public String create(Project project) {
		project.setId(java.util.UUID.randomUUID().toString());
		InMemoryPersistence.put(project);
		return project.id;
	}

	@Override
	public Project save(Project project) {
		assignCardIds(project);
		project.incrementVersion();
		InMemoryPersistence.put(project);
		return project;
	}

	@Override
	public Project load(String id) {
		Logger.debug("load(String id) id=" + id);
		return InMemoryPersistence.getProject(id);
	}

	@Override
	public List<Project> loadByOwner(String username) {
		Logger.debug("loadByOwner(String owner) owner=" + username);
		List<Project> result = new ArrayList<>();
		
		for (Project project : InMemoryPersistence.getProjects()) {
			if (project.username.equals(username)) {
				result.add(project);
			}
		}
		return result;
	}

	public static List<Project> all() {
		return new ArrayList<Project>(InMemoryPersistence.getProjects());
	}
	

	@Override
	public void delete(String id) {
		InMemoryPersistence.removeProject(id);
	}

	@Override
	public int countProjectsByOwner(String username) {
		// TODO Auto-generated method stub
		return loadByOwner(username).size();
	}

	@Override
	public int countContributionsByOwner(String username) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Project> loadByOwner(String username, int offset, int length) {
		// TODO Auto-generated method stub
		return loadByOwner(username);
	}

	@Override
	public List<Contribution> loadContributions(String username, int offset,
			int length) {
		// TODO Auto-generated method stub
		return new ArrayList<Contribution>();
	}

	@Override
	public void favorite(String username, String projectId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Favorite> favorites(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
