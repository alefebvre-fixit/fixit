package com.fixit.service.impl;

import java.util.List;

import com.fixit.model.Card;
import com.fixit.model.Contribution;
import com.fixit.model.Project;

public class BaseProjectService {

	protected void assignCardIds(Project project) {
		List<Card> cards = project.getCards();
		for (Card card : cards) {
			if (card.getId() == null) {
				card.setId(java.util.UUID.randomUUID().toString());
			}
			List<Contribution> contributions = card.getContributions();
			for (Contribution contribution : contributions) {
				if (contribution.getId() == null) {
					contribution.setId(java.util.UUID.randomUUID().toString());
				}
			}
		}
	}
	
}
