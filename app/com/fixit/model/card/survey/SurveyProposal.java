package com.fixit.model.card.survey;

import com.fixit.model.Contribution;

public class SurveyProposal {

	private String id;
	private String name;
	private String description;
	private String color;
	private int votes;
	
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public void vote() {
		this.votes += 1;
	}

	public boolean cancel(SurveyContribution contribution) {
		if (contribution != null && contribution.getVotes().contains(getId())) {
			contribution.setStatus(Contribution.STATUS_CANCELED);
			this.votes -= 1;
			return true;
		}
		return false;
	}

}
