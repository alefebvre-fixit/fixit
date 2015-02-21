package com.fixit.model;

import java.util.Date;

import org.mongojack.Id;
import org.mongojack.ObjectId;

public class Favorite {

	private String projectId;
	private String username;
	private Date creationDate;
	public String id;

	@Id
	@ObjectId
	public String getId() {
		return id;
	}

	@Id
	@ObjectId
	public void setId(String id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	
	
}
