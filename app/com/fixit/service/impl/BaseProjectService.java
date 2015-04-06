package com.fixit.service.impl;

import java.util.List;

import com.fixit.model.Card;
import com.fixit.model.Project;
import com.fixit.model.card.availability.AvailabilityCard;
import com.fixit.model.card.availability.AvailabilityProposal;
import com.fixit.model.card.survey.SurveyCard;
import com.fixit.model.card.survey.SurveyProposal;

public class BaseProjectService {

	protected void assignCardIds(Project project) {
		List<Card> cards = project.getCards();
		for (Card card : cards) {
			
			if (card.getId() == null) {
				card.setId(java.util.UUID.randomUUID().toString());
			}

			/*
			List<Contributable<? extends Contribution>> contributables = card.getContributables();
			for (Contributable<? extends Contribution> contributable : contributables) {
				List<? extends Contribution> contributions = contributable.getContributions();
				for (Contribution contribution : contributions) {
					if (contribution.getId() == null) {
						contribution.setId(java.util.UUID.randomUUID().toString());
					}
				}
			}
			*/
			
			if (card instanceof AvailabilityCard){
				AvailabilityCard dateCard = (AvailabilityCard) card;
				for (AvailabilityProposal proposal : dateCard.getProposals()) {
					if (proposal.getId() == null) {
						proposal.setId(java.util.UUID.randomUUID().toString());
					}
				}
			}
			else if (card instanceof SurveyCard){
				SurveyCard surveyCard = (SurveyCard) card;
				for (SurveyProposal proposal : surveyCard.getProposals()) {
					if (proposal.getId() == null) {
						proposal.setId(java.util.UUID.randomUUID().toString());
					}
				}
			}
			
		}
	}
	
}
