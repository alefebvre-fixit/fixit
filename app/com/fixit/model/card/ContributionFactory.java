package com.fixit.model.card;

import java.util.Date;

import com.fixit.model.Contribution;

public class ContributionFactory {

	public static final Contribution createContribution(String type, String projectId, String cardId, String username) {

		Contribution result = null;

		if (ItemContribution.TYPE.equals(type)) {
			result = new ItemContribution();
		} else if (DateContribution.TYPE.equals(type)) {
			result = new DateContribution();
		} else if (ParticipantContribution.TYPE.equals(type)) {
			result = new ParticipantContribution();
		}

		if (result != null){
			result.setCardId(cardId);
			result.setProjectId(projectId);
			result.setDate(new Date());
			result.setContributor(username);
		}
		
		return result;
	}

}