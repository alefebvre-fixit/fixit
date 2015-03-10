package com.fixit.model;

import java.util.List;

import org.mongojack.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fixit.model.card.DateCard;
import com.fixit.model.card.ItemCard;
import com.fixit.model.card.ParticipantCard;
import com.fixit.model.card.SurveyCard;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ 
		@Type(value = DateCard.class, name = DateCard.TYPE),
		@Type(value = ItemCard.class, name = ItemCard.TYPE),
		@Type(value = ParticipantCard.class, name = ParticipantCard.TYPE),
		@Type(value = SurveyCard.class, name = SurveyCard.TYPE) 

})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Card {

	public static final String STATUS_NEW = "New";

	
	public String type = "default";

	private String id;
	private String projectId;
	private String username;
	private String status = STATUS_NEW;
	private int contributions;

	@Id
	public String getId() {
		return id;
	}

	@Id
	public void setId(String id) {
		this.id = id;
	}

	@JsonIgnore
	public abstract boolean cancel(Contribution contribution);

	@JsonIgnore
	public abstract boolean isOpenForContribution(
			List<Contribution> contributions);

	@JsonIgnore
	public abstract boolean contribute(Contribution contribution,
			List<Contribution> contributions);

	public int getContributions() {
		return contributions;
	}

	public void setContributions(int contributions) {
		this.contributions = contributions;
	}

	@JsonIgnore
	public void incrementContributions() {
		this.contributions += 1;
	}

	@JsonIgnore
	public void decrementContributions() {
		if (contributions >= 0) {
			this.contributions -= 1;
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
