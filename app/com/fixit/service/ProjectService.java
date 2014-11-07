package com.fixit.service;

import java.util.List;

import com.fixit.model.Project;

public interface ProjectService {

	public List<Project> getAll();

	public String create(Project project);

	public Project save(Project project);
	
	public void delete(String id);

	public Project load(String id);
	
	public List<Project> loadByOwner(String username);

}
