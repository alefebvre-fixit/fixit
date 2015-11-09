package com.fixit.model.notification;

import java.util.Date;

public class Notification implements Cloneable {

	public static final String TYPE_CONTRIBUTION = "Contribution";
	public static final String TYPE_PROJECT = "Project";
	public static final String TYPE_CARD = "Card";
	public static final String TYPE_FAVORITE = "Favorite";
	public static final String TYPE_FOLLOWERS = "Follow";
	public static final String TYPE_COMMENTS = "Comment";
	
	
	public static final String TYPE_GROUP = "Group";
	public static final String TYPE_EVENT = "Event";
	public static final String TYPE_PARTICIPATION = "Participation";

	private String id;
	private String username;
	private String actor;
	private String type;
	private String subtype;
	private String status;
	private String projectId;
	private String cardId;
	private Date notificationDate;
	private String title;
	private String description;
	private String eventId;
	private String groupId;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
