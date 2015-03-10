package com.fixit.model.card;

import java.util.ArrayList;
import java.util.List;

import com.fixit.model.Card;
import com.fixit.model.Contribution;

public class SurveyCard extends Card {

	public static final String TYPE = "survey";

	public SurveyCard() {
		this.type = TYPE;
	}

	private String name;
	private String proposal;
	private int votes = 0;

	private List<SurveyProposal> proposals = new ArrayList<SurveyProposal>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProposal() {
		return proposal;
	}

	public void setProposal(String proposal) {
		this.proposal = proposal;
	}

	public List<SurveyProposal> getProposals() {
		return proposals;
	}

	public void setProposals(List<SurveyProposal> proposals) {
		this.proposals = proposals;
	}

	public SurveyProposal getProposal(String proposalId) {

		if (proposalId == null) {
			return null;
		}

		for (SurveyProposal proposal : proposals) {
			if (proposalId.equals(proposal.getId())) {
				return proposal;
			}
		}

		return null;
	}

	public int getVotes() {
		return votes;
	}

	@Override
	public boolean cancel(Contribution contribution) {
		boolean result = false;
		if (contribution instanceof SurveyContribution) {
			SurveyContribution surveyContribution = (SurveyContribution) contribution;
			List<String> proposalIds = surveyContribution.getVotes();
			for (String proposalId : proposalIds) {
				SurveyProposal proposal = getProposal(proposalId);
				if (proposal != null) {
					if (proposal.cancel(surveyContribution)) {
						result = true;
					}
				}
			}
			return result;
		}
		return result;
	}

	@Override
	public boolean contribute(Contribution contribution,
			List<Contribution> contributions) {

		if (!isOpenForContribution(contributions)) {
			return false;
		}

		if (contribution instanceof SurveyContribution) {
			SurveyContribution surveyContribution = (SurveyContribution) contribution;
			List<String> proposalIds = surveyContribution.getVotes();
			for (String proposalId : proposalIds) {
				SurveyProposal proposal = getProposal(proposalId);
				if (proposal != null) {
					proposal.vote();
				}
			}

			if (surveyContribution.getVotes().size() > 0) {
				votes++;
				incrementContributions();
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean isOpenForContribution(List<Contribution> contributions) {

		if (contributions != null && contributions.size() > 0) {
			return false;
		}

		return true;
	}

}
