package com.fixit.model.card;

import java.util.ArrayList;
import java.util.List;

import com.fixit.model.Contribution;

public class SurveyContribution extends Contribution {

	public static final String TYPE = SurveyCard.TYPE;

	private List<String> votes = new ArrayList<String>();

	public SurveyContribution() {
		this.type = TYPE;
	}

	public List<String> getVotes() {
		return votes;
	}

	public void setVotes(List<String> votes) {
		this.votes = votes;
	}

	@Override
	public boolean merge(List<Contribution> contributions) {
		// We do not allow merge for votes
		return false;
	}

}
