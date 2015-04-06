package com.fixit.model.card.availability;

import java.util.Date;

import com.fixit.model.Contribution;

public class AvailabilityProposal {

	private String id;
	private Date date;
	private int votes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public boolean cancel(AvailabilityContribution contribution) {
		if (contribution != null && contribution.getVotes().contains(getId())) {
			contribution.setStatus(Contribution.STATUS_CANCELED);
			this.votes -= 1;
			return true;
		}
		return false;
	}

}
