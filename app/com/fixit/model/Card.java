package com.fixit.model;

import java.util.ArrayList;
import java.util.List;

import org.mongojack.Id;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fixit.model.card.DateCard;
import com.fixit.model.card.ItemCard;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = DateCard.class, name = "date"),
		@Type(value = ItemCard.class, name = "item") })
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
	public List<Contribution> contributions = new ArrayList<Contribution>();

	public List<CardEvent> getEvents() {
		return events;
	}

	public void setEvents(List<CardEvent> events) {
		this.events = events;
	}

	public List<Contribution> getContributions() {
		return contributions;
	}

	public void setContributions(List<Contribution> contributions) {
		this.contributions = contributions;
	}
	
	public Contribution getContribution(String contributionId){
		for (Contribution contribution : contributions) {
			if (contribution.getId().equals(contributionId)){
				return contribution;
			}
		}
		return null;
	}
	
	public abstract boolean cancel(Contribution contribution);
	
}
