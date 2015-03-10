package com.fixit.model.card.graph;

import com.fixit.model.Card;
import com.fixit.model.card.DateCard;
import com.fixit.model.card.SurveyCard;
import com.fixit.model.card.graph.impl.DateGraphDataFactory;
import com.fixit.model.card.graph.impl.DefaultGraphDataFactory;
import com.fixit.model.card.graph.impl.SurveyGraphDataFactory;

public abstract class GraphDataFactory {

	public abstract GraphData createGraphData(Card card);

	private final static GraphDataFactory dateFactory = new DateGraphDataFactory();
	private final static GraphDataFactory surveyFactory = new SurveyGraphDataFactory();
	private final static GraphDataFactory defaultFactory = new DefaultGraphDataFactory();

	public static GraphDataFactory getInstance(Card card) {
		GraphDataFactory result = null;

		if (card instanceof DateCard) {
			result = dateFactory;
		} else if (card instanceof SurveyCard) {
			result = surveyFactory;
		} else {
			result = defaultFactory;
		}

		return result;
	}

}
