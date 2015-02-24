package com.fixit.model.card;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fixit.model.Card;
import com.fixit.model.Contributable;
import com.fixit.model.Contribution;
import com.fixit.model.Votable;
import com.fixit.model.Vote;

public class DateCard extends Card implements Votable{

	public static final String TYPE = "date";

	public DateCard() {
		this.type = TYPE;
	}

	private String name;
	private boolean open = false;
	private Date date;
	private int votes = 0;
	private List<DateProposal> proposals = new ArrayList<DateProposal>();
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<DateProposal> getProposals() {
		return proposals;
	}

	public void setProposals(List<DateProposal> proposals) {
		this.proposals = proposals;
	}

	public DateProposal getProposal(String proposalId) {

		if (proposalId == null) {
			return null;
		}

		for (DateProposal proposal : proposals) {
			if (proposalId.equals(proposal.getId())) {
				return proposal;
			}
		}

		return null;
	}


	@JsonIgnore
	public int calculateVotes() {
		int result = 0;
		for (DateProposal proposal : proposals) {
			result += proposal.calculateVotes();
		}
		return result;
	}

	public int getVotes() {
		return votes;
	}

	@Override
	public int getContributionSize() {
		return getVotes();
	}

	@Override
	public Contribution getContribution(String contributionId) {
		for (DateProposal proposal : proposals) {
			Contribution contribution = proposal
					.getContribution(contributionId);
			if (contribution != null) {
				return contribution;
			}
		}
		return null;
	}

	@Override
	public boolean cancel(String contributionId) {
		for (DateProposal proposal : proposals) {
			if (proposal.cancel(contributionId)){
				this.votes = calculateVotes();
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Contributable<? extends Contribution>> getContributables() {
		List<Contributable<? extends Contribution>> result = new ArrayList<Contributable<? extends Contribution>>();
		result.addAll(proposals);
		return result;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	@Override
	public void submit(Vote vote){
		List<String> proposalIds = vote.getProposals();
		for (String proposalId : proposalIds) {
			DateProposal proposal = getProposal(proposalId);
			if (proposal != null){
				proposal.vote(vote.getUsername());
			}
		}
		this.votes = calculateVotes();
	}

}
