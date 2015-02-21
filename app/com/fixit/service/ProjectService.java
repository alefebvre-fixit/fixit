package com.fixit.service;

import java.util.List;

import com.fixit.model.Contribution;
import com.fixit.model.Favorite;
import com.fixit.model.Project;
import com.fixit.model.User;

public interface ProjectService {

	public List<Project> getAll();

	public String create(Project project);

	public Project save(Project project);
	
	public void delete(String id);

	public Project load(String id);
	
	public List<Project> loadByOwner(String username);
	
	public int countProjectsByOwner(String username);
	
	public int countContributionsByOwner(String username);
	
	public List<Project> loadByOwner(String username, int offset, int length);
	
	public List<Contribution> loadContributions(String username, int offset, int length); 

	public void follow(String username, String projectId);
	
	public void unfollow(String username, String projectId);
	
	public List<String> favorites(String username);
	
}
