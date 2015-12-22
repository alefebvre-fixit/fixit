package com.fixit.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fixit.model.Card;
import com.fixit.model.Project;

public class InMemoryPersistence {

	private static final Map<String, Project> projects = new HashMap<String, Project>();

	public static final List<Project> getProjects() {
		return new ArrayList<Project>(projects.values());
	}

	public static final Project getProject(String id) {
		return projects.get(id);
	}
	
	public static final void put(Project project){
		projects.put(project.getId(), project);
	}
	
	public static final void removeProject(String projectId){
		if (projects.containsKey(projectId)){
			projects.remove(projectId);
		}
	}
	
	public static final Card getCard(String id){
		Card result = null;
		List<Project>  myProjects = getProjects();
		for (Project project : myProjects) {
			if (project.getCard(id) != null){
				return project.getCard(id);
			}
		}
		return result;
	}
	
	

}
