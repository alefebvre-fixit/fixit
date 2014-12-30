package com.fixit.model.card;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fixit.model.Card;
import com.fixit.model.Contribution;

public class DateCard extends Card<DateContribution> {

	public DateCard() {
		this.type = "date";
	}
	
	private Date date;
	private int votes= 0;
	private List<DateProposal> proposals = new ArrayList<DateProposal>();
	
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
		
		if (proposalId == null){
			return null;
		}
		
		for (DateProposal proposal : proposals) {
			if (proposalId.equals(proposal.getId())){
				return proposal;
			}
		}
		
		return null;
	}

	@Override
	public boolean cancel(Contribution contribution) {
		contribution.setStatus(Contribution.STATUS_CANCELED);
		return true;
	}
	
	
	@JsonIgnore
	public int calculateVotes() {
		int result = 0;
		for (DateProposal proposal : proposals) {
			result += proposal.calculateVotes();
		}
		return result;
	}

	public void vote(String username, String proposalId) {
		DateProposal proposal = getProposal(proposalId);
		if (proposal != null){
			proposal.vote(username);
		}
		this.votes = calculateVotes();
	}

	public int getVotes() {
		return votes;
	}

	
	
}
