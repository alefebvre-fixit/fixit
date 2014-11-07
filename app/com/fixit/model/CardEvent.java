package com.fixit.model;

import javax.persistence.Entity;

import play.db.ebean.Model;

@Entity
public class CardEvent extends Model {

	private static final long serialVersionUID = 1L;

	public String username;
	public String description;

	public CardEvent() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
