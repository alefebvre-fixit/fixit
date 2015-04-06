package com.fixit.model.card;

import java.util.Date;

import com.fixit.model.Contribution;
import com.fixit.model.card.advice.AdviceContribution;
import com.fixit.model.card.advice.AdviceLikeContribution;
import com.fixit.model.card.availability.AvailabilityContribution;
import com.fixit.model.card.item.ItemContribution;
import com.fixit.model.card.money.MoneyContribution;
import com.fixit.model.card.participant.ParticipantContribution;
import com.fixit.model.card.survey.SurveyContribution;

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
		} else if (AdviceContribution.TYPE.equals(type)) {
			result = new AdviceContribution();
		} else if (AdviceLikeContribution.TYPE.equals(type)) {
			result = new AdviceLikeContribution();
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
