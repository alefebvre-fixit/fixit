package com.fixit.model.account;

import java.util.List;

import com.fixit.model.Contribution;
import com.fixit.model.Project;
import com.fixit.model.User;
import com.fixit.model.group.Group;

public class UserSummary {

	private User user;

	private int contributionNumber = 0;
	private List<Contribution> lastContribution;

	private int projectNumber;
	private List<Project> lastProjects;

	private int groupNumber;
	private List<Group> lastGroups;

	private int followerNumber = 0;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getContributionNumber() {
		return contributionNumber;
	}

	public void setContributionNumber(int contributionNumber) {
		this.contributionNumber = contributionNumber;
	}

	public List<Contribution> getLastContribution() {
		return lastContribution;
	}

	public void setLastContribution(List<Contribution> lastContribution) {
		this.lastContribution = lastContribution;
	}

	public int getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(int projectNumber) {
		this.projectNumber = projectNumber;
	}

	public List<Project> getLastProjects() {
		return lastProjects;
	}

	public void setLastProjects(List<Project> lastProjects) {
		this.lastProjects = lastProjects;
	}

	public int getFollowerNumber() {
		return followerNumber;
	}

	public void setFollowerNumber(int followerNumber) {
		this.followerNumber = followerNumber;
	}

	public int getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(int groupNumber) {
		this.groupNumber = groupNumber;
	}

	public List<Group> getLastGroups() {
		return lastGroups;
	}

	public void setLastGroups(List<Group> lastGroups) {
		this.lastGroups = lastGroups;
	}

}
