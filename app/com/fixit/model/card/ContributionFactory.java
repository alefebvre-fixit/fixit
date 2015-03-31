package com.fixit.model.card;

import java.util.Date;

import com.fixit.model.Contribution;

public class ContributionFactory {

	public static final Contribution createContribution(String type,
			String projectId, String cardId, String username) {

		Contribution result = null;

		if (ItemContribution.TYPE.equals(type)) {
			result = new ItemContribution();
		} else if (AvailabilityContribution.TYPE.equals(type)) {
			result = new AvailabilityContribution();
		} else if (ParticipantContribution.TYPE.equals(type)) {
			result = new ParticipantContribution();
		} else if (SurveyContribution.TYPE.equals(type)) {
			result = new SurveyContribution();
		} else if (MoneyContribution.TYPE.equals(type)) {
			result = new MoneyContribution();
		}

		if (result != null) {
			result.setCardId(cardId);
			result.setProjectId(projectId);
			result.setDate(new Date());
			result.setContributor(username);
		}

		return result;
	}

}
