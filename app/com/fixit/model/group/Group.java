package com.fixit.model.group;

import javax.persistence.Entity;

import org.mongojack.Id;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fixit.model.User;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Group {

	public static final String STATUS_NEW = "New";
	public static final String STATUS_PUBLISHED = "Published";
	public static final String STATUS_DRAFT = "Draft";

	public String id;
	public double version = 0;
	private String status = STATUS_NEW;

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

	public double getVersion() {
		return version;
	}

	public void incrementVersion() {
		this.version++;
	}

	public String name;
	public String description;

	public String city;
	public String country;

	public String username;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setVersion(double version) {
		this.version = version;
	}

	public static Group instanciate(User user) {
		Group project = new Group();
		project.username = user.getUsername();
		project.country = user.getProfile().getCountry();
		project.city = user.getProfile().getCity();

		project.name = "new project 2";
		project.description = "";

		return project;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}