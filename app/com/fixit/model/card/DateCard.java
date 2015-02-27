package com.fixit.model.card;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fixit.model.Card;
import com.fixit.model.Votable;
import com.fixit.model.Vote;

public class DateCard extends Card<DateContribution> implements
		Votable<DateContribution> {

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

	public int getVotes() {
		return votes;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public DateContribution submit(Vote vote) {

		List<String> proposalIds = vote.getProposals();

		DateContribution contribution = new DateContribution();
		contribution.setCardId(getId());
		contribution.setContributor(vote.getUsername());

		for (String proposalId : proposalIds) {
			DateProposal proposal = getProposal(proposalId);
			if (proposal != null) {
				contribution.getVotes().add(proposalId);
				proposal.vote();
			}
		}

		if (contribution.getVotes().size() > 0) {
			votes++;
			incrementContributions();
			return contribution;
		}

		return null;
	}

	@Override
	public boolean cancel(DateContribution contribution) {
		boolean result = false;

		List<String> proposalIds = contribution.getVotes();
		for (String proposalId : proposalIds) {
			DateProposal proposal = getProposal(proposalId);
			if (proposal != null) {
				if (proposal.cancel(contribution)) {
					result = true;
				}
			}
		}

		return result;
	}

}
