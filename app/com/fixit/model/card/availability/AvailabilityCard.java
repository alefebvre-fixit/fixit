package com.fixit.model.card.availability;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fixit.model.Card;
import com.fixit.model.Contribution;

public class AvailabilityCard extends Card {

	public static final String TYPE = "availability";

	public AvailabilityCard() {
		this.type = TYPE;
	}

	private boolean open = true;
	private Date date;
	private int votes = 0;
	
	private List<AvailabilityProposal> proposals = new ArrayList<AvailabilityProposal>();

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<AvailabilityProposal> getProposals() {
		return proposals;
	}

	public void setProposals(List<AvailabilityProposal> proposals) {
		this.proposals = proposals;
	}

	public AvailabilityProposal getProposal(String proposalId) {

		if (proposalId == null) {
			return null;
		}

		for (AvailabilityProposal proposal : proposals) {
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
		if (contribution instanceof AvailabilityContribution) {
			AvailabilityContribution dateContribution = (AvailabilityContribution) contribution;
			List<String> proposalIds = dateContribution.getVotes();
			for (String proposalId : proposalIds) {
				AvailabilityProposal proposal = getProposal(proposalId);
				if (proposal != null) {
					if (proposal.cancel(dateContribution)) {
						decrementContributions();
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
		
		if (contribution instanceof AvailabilityContribution) {
			AvailabilityContribution dateContribution = (AvailabilityContribution) contribution;
			List<String> proposalIds = dateContribution.getVotes();
			for (String proposalId : proposalIds) {
				AvailabilityProposal proposal = getProposal(proposalId);
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
