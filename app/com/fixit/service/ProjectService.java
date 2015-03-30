package com.fixit.service;

import java.util.List;

import com.fixit.model.Project;
import com.fixit.model.User;
import com.fixit.model.account.UserCard;

public interface ProjectService {

	public List<Project> getAll();

	public String create(Project project);

	public Project save(Project project);

	public void delete(String id);

	public Project getProject(String id);

	public int countProjectsByOwner(String username);

	public List<Project> getUserProjects(String username, int offset, int length);

	public void follow(String username, String projectId);

	public void unfollow(String username, String projectId);

	public List<String> projectFollowed(String username);

	public List<User> projectFollowers(String projectId);

	public List<String> projectFollowerNames(String projectId);
	
	public int projectFollowersSize(String projectId);

	public String getProjectOwner(String projectId);

}
