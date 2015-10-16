package com.fixit.model.event;


import javax.persistence.Entity;

import org.mongojack.Id;
import org.mongojack.ObjectId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fixit.model.User;
import com.fixit.model.group.Group;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

	public static final String STATUS_NEW = "New";
	public static final String STATUS_PUBLISHED = "Published";
	public static final String STATUS_DRAFT = "Draft";

	public String id;
	public String groupId;
	public String groupName;
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

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	public static Event instanciate(Group group, User user) {
		Event event = new Event();
		event.username = user.getUsername();
		event.country = user.getProfile().getCountry();
		event.city = user.getProfile().getCity();

		event.name = "new event 2";
		event.description = "";

		return event;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public boolean accept(Participation participation){
		return true;
	}

}