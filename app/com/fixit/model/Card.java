package com.fixit.model;

import org.mongojack.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fixit.model.card.DateCard;
import com.fixit.model.card.ItemCard;
import com.fixit.model.card.ParticipantCard;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ 
		@Type(value = DateCard.class, name = DateCard.TYPE),
		@Type(value = ItemCard.class, name = ItemCard.TYPE),
		@Type(value = ParticipantCard.class, name = ParticipantCard.TYPE) 
		})
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Card<C extends Contribution> {

	public String type = "default";

	private String id;
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
	public abstract boolean cancel(C contribution);

	public int getContributions() {
		return contributions;
	}

	public void setContributions(int contributions) {
		this.contributions = contributions;
	}

	@JsonIgnore
	public void incrementContributions(){
		this.contributions += 1;
	}
	
	@JsonIgnore
	public void decrementContributions(){
		if (contributions >= 0){
			this.contributions -= 1;
		}
	}

}
