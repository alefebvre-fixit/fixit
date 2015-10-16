package com.fixit.model.event;

import java.util.Date;

import org.mongojack.Id;
import org.mongojack.ObjectId;

public class Participation {

	public static final String STATUS_RSVP = "RSVP";
	public static final String STATUS_IN = "IN";
	public static final String STATUS_OUT = "OUT";

	
	private String eventId;
	private String username;
	private Date creationDate;
	private Date modificationDate;
	private String status;
	private String comment;

	public String id;
	public double version = 0;

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

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
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

	public void reconcile(Participation original) {
		this.id = original.getId();
		this.version = original.getVersion();
		this.creationDate = original.getCreationDate();
		incrementVersion();
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}