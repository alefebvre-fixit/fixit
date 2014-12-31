package com.fixit.model.card;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fixit.model.Contributable;
import com.fixit.model.Contribution;
import com.fixit.model.ContributionHolder;

public class DateProposal implements Contributable<DateContribution> {

	private final ContributionHolder<DateContribution> contributions = new ContributionHolder<DateContribution>();

	private int id;
	private Date date;
	private int votes;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	@JsonIgnore
	public int calculateVotes() {
		return contributions.getValidContributions().size();
	}

	public void vote(String username) {
		DateContribution contribution = contributions
				.getContributionByOwner(username);
		if (contribution != null) {
			contribution.validate();
		} else {
			contribution = new DateContribution();
			contribution.setContributor(username);
			contribution.setDate(new Date());
			contributions.add(contribution);
		}
		votes = calculateVotes();
	}

	public Contribution getContribution(String contributionId) {
		return contributions.getContribution(contributionId);
	}

	@Override
	public List<DateContribution> getContributions() {
		return contributions.getContributions();
	}

	@Override
	public void setContributions(List<DateContribution> contributions) {
		this.contributions.setContributions(contributions);
	}

}
