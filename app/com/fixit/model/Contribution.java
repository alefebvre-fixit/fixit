package com.fixit.model;

import java.util.Date;

import org.mongojack.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fixit.model.card.ItemContribution;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = ItemContribution.class, name = ItemContribution.TYPE) })
public abstract class Contribution {

	public static final String STATUS_NEW = "New";
	public static final String STATUS_CANCELED = "Canceled";
	
	public String type = "default";
	public String status = STATUS_NEW;
	
	private String contributor;
	private Date date;
	private String id;

	public Contribution(){
	}
	
	@Id
	public String getId() {
		return id;
	}
	
	@Id
	public void setId(String id) {
		this.id = id;
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
	public boolean isValid(){
		return !STATUS_CANCELED.equals(status);
	}
	
	@JsonIgnore
	public void validate(){
		status = STATUS_NEW;
	}

}
