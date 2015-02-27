package com.fixit.service;

import java.util.List;

import com.fixit.model.Contribution;
import com.fixit.model.Project;

public interface ProjectService {

	public List<Project> getAll();

	public String create(Project project);

	public Project save(Project project);
	
	public void delete(String id);

	public Project load(String id);	
	
	public int countProjectsByOwner(String username);
	
	public int countContributionsByOwner(String username);
	
	public List<Project> getUserProjects(String username, int offset, int length);
	
	public List<Contribution> getUserContributions(String username, int offset, int length); 

	public void follow(String username, String projectId);
	
	public void unfollow(String username, String projectId);
	
	public List<String> favorites(String username);
	
	public List<Contribution> getProjectContributions(String username, String projectId);
	
	public List<Contribution> getProjectContributions(String projectId);
	
	public List<Contribution> getCardContributions(String cardId);
	
	public Contribution getContribution(String contributionId);

	public Contribution saveContribution(Contribution contribution);

	public void deleteContribution(String id);
	

}
