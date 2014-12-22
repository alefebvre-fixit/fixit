package com.fixit.service;

import java.util.List;

import com.fixit.model.Project;

public interface CardService {
	
	public void delete(String id);

	public Project load(String id);
	
	public List<Project> loadByProject(String projectId);

}
