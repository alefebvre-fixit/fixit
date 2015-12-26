package com.fixit.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fixit.model.card.advice.AdviceContribution;
import com.fixit.model.card.advice.AdviceLikeContribution;
import com.fixit.model.card.availability.AvailabilityContribution;
import com.fixit.model.card.item.ItemContribution;
import com.fixit.model.card.money.MoneyContribution;
import com.fixit.model.card.participant.ParticipantContribution;
import com.fixit.model.card.survey.SurveyContribution;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
		@Type(value = ItemContribution.class, name = ItemContribution.TYPE),
		@Type(value = AvailabilityContribution.class, name = AvailabilityContribution.TYPE),
		@Type(value = ParticipantContribution.class, name = ParticipantContribution.TYPE),
		@Type(value = SurveyContribution.class, name = SurveyContribution.TYPE),
		@Type(value = AdviceContribution.class, name = AdviceContribution.TYPE),
		@Type(value = AdviceLikeContribution.class, name = AdviceLikeContribution.TYPE),
		@Type(value = MoneyContribution.class, name = MoneyContribution.TYPE)
		})
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Contribution {

	public static final String STATUS_NEW = "New";
	public static final String STATUS_CANCELED = "Canceled";
	public static final String STATUS_UPDATED = "Updated";

	public String type = "default";
	public String status = STATUS_NEW;

	private String contributor;
	private Date date;
	
	public String id;
	
	public abstract boolean merge(List<Contribution> contributions);

	public Contribution() {
	}

	public String getContributor() {
		return contributor;
	}

	public void setContributor(String contributor) {
		this.contributor = contributor;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@JsonIgnore
	public boolean isValid() {
		return !STATUS_CANCELED.equals(status);
	}

	@JsonIgnore
	public void validate() {
		status = STATUS_NEW;
	}
	
	private String projectId;
	private String cardId;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
