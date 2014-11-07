package com.fixit.service.impl;

import java.util.ArrayList;
import java.util.List;

import play.Logger;

import com.fixit.model.Project;
import com.fixit.service.ProjectService;

public class TestProjectService implements ProjectService {

	@Override
	public List<Project> getAll() {
		return all();
	}

	@Override
	public String create(Project project) {
		// TODO Auto-generated method stub
		projects.add(project);
		return project.id;
	}

	@Override
	public Project save(Project project) {
		// TODO Auto-generated method stub
		return project;
	}

	@Override
	public Project load(String id) {
		Logger.debug("load(String id) id=" + id);
		for (Project project : projects) {
			if (project.id.equals(id)) {
				return project;
			}
		}
		return null;
	}

	@Override
	public List<Project> loadByOwner(String owner) {
		Logger.debug("loadByOwner(String owner) owner=" + owner);
		List<Project> result = new ArrayList<>();
		for (Project project : projects) {
			if (project.username.equals(owner)) {
				result.add(project);
			}
		}
		return result;
	}

	public static List<Project> all() {
		return projects;
	}

	public static final List<Project> projects = Project.all();

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

}
