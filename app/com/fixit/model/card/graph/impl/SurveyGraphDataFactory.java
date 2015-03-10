package com.fixit.model.card.graph.impl;

import java.util.List;

import com.fixit.model.Card;
import com.fixit.model.card.SurveyCard;
import com.fixit.model.card.SurveyProposal;
import com.fixit.model.card.graph.GraphData;
import com.fixit.model.card.graph.GraphDataFactory;

public class SurveyGraphDataFactory extends GraphDataFactory {

	@Override
	public GraphData createGraphData(Card card) {
		GraphData result = null;
		if (card instanceof SurveyCard) {
			SurveyCard surveyCard = (SurveyCard) card;

			result = new GraphData();
			List<SurveyProposal> proposals = surveyCard.getProposals();
			if (proposals != null && proposals.size() > 0) {
				for (SurveyProposal surveyProposal : proposals) {
					result.getLabels().add(surveyProposal.getName());
					result.getDatas().add(surveyProposal.getVotes());
				}
			}
		}

		return result;
	}

}
