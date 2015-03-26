package com.fixit.model.card.graph.impl;

import java.util.List;

import com.fixit.model.Card;
import com.fixit.model.card.DateCard;
import com.fixit.model.card.DateProposal;
import com.fixit.model.card.graph.GraphData;
import com.fixit.model.card.graph.GraphDataFactory;

public class DateGraphDataFactory extends GraphDataFactory {

	@Override
	public GraphData createGraphData(Card card) {
		GraphData result = null;
		if (card instanceof DateCard) {
			DateCard dateCard = (DateCard) card;

			result = new GraphData();
			List<DateProposal> proposals = dateCard.getProposals();
			if (proposals != null && proposals.size() > 0) {
				for (DateProposal dateProposal : proposals) {
					// TODO Change
					result.getLabels().add(dateProposal.getDate().toString());
					result.getDatas().add(dateProposal.getVotes());
				}
			}
		}
		assignColors(result);
		return result;
	}

}
