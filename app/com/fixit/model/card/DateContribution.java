package com.fixit.model.card;

import com.fixit.model.Contribution;

public class DateContribution extends Contribution {

	public DateContribution() {
		this.type = "date";
	}

	private int proposalId;

	public int getProposalId() {
		return proposalId;
	}

	public void setProposalId(int proposalId) {
		this.proposalId = proposalId;
	}

}