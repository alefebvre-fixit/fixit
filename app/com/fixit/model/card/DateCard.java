package com.fixit.model.card;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fixit.model.Card;
import com.fixit.model.Contribution;

public class DateCard extends Card {

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
	public boolean cancel(Contribution contribution) {
		boolean result = false;
		if (contribution instanceof DateContribution) {
			DateContribution dateContribution = (DateContribution) contribution;
			List<String> proposalIds = dateContribution.getVotes();
			for (String proposalId : proposalIds) {
				DateProposal proposal = getProposal(proposalId);
				if (proposal != null) {
					if (proposal.cancel(dateContribution)) {
						result = true;
					}
				}
			}
			return result;
		}
		return result;
	}

	@Override
	public boolean contribute(Contribution contribution, List<Contribution> contributions) {
		
		if (!isOpenForContribution(contributions)){
			return false;
		}
		
		if (contribution instanceof DateContribution) {
			DateContribution dateContribution = (DateContribution) contribution;
			List<String> proposalIds = dateContribution.getVotes();
			for (String proposalId : proposalIds) {
				DateProposal proposal = getProposal(proposalId);
				if (proposal != null) {
					proposal.vote();
				}
			}

			if (dateContribution.getVotes().size() > 0) {
				votes++;
				incrementContributions();
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isOpenForContribution(List<Contribution> contributions) {
		
		if (!open){
			return false;
		}
		
		if (contributions != null && contributions.size() > 0){
			return false;
		}
		
		return true;
	}

}
