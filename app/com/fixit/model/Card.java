package com.fixit.model;

import java.util.ArrayList;
import java.util.List;

import org.mongojack.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fixit.model.card.DateCard;
import com.fixit.model.card.ItemCard;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = DateCard.class, name = "date"),
		@Type(value = ItemCard.class, name = "item") })
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Card {

	public String type = "default";

	public String id;

	@Id
	public String getId() {
		return id;
	}

	@Id
	public void setId(String id) {
		this.id = id;
	}

	public List<CardEvent> events = new ArrayList<CardEvent>();

	public List<CardEvent> getEvents() {
		return events;
	}

	public void setEvents(List<CardEvent> events) {
		this.events = events;
	}

	/*
	 * public List<C> contributions = new ArrayList<C>(); public List<C>
	 * getContributions() { return contributions; }
	 * 
	 * @JsonIgnore public List<C> getValidContributions() { List<C> result = new
	 * ArrayList<C>();
	 * 
	 * for (C contribution : contributions) { if (contribution.isValid()) {
	 * result.add(contribution); } }
	 * 
	 * return result; }
	 * 
	 * public int getContributionSize() { List<C> validContributions =
	 * getValidContributions(); return validContributions.size(); }
	 * 
	 * public void setContributions(List<C> contributions) { this.contributions
	 * = contributions; }
	 * 
	 * public Contribution getContribution(String contributionId) { for
	 * (Contribution contribution : contributions) { if
	 * (contribution.getId().equals(contributionId)) { return contribution; } }
	 * return null; }
	 * 
	 * public abstract boolean cancel(Contribution contribution);
	 */
	
	public abstract int getContributionSize();
	
	@JsonIgnore
	public abstract Contribution getContribution(String contributionId);
	
	@JsonIgnore
	public abstract boolean cancel(String contributionId);

	@JsonIgnore
	public abstract List<Contributable<? extends Contribution>> getContributables();
	
}
