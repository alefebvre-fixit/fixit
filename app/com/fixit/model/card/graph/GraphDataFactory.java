package com.fixit.model.card.graph;

import com.fixit.model.Card;
import com.fixit.model.card.AvailabilityCard;
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

		if (card instanceof AvailabilityCard) {
			result = dateFactory;
		} else if (card instanceof SurveyCard) {
			result = surveyFactory;
		} else {
			result = defaultFactory;
		}

		return result;
	}

	public void assignColors(GraphData graph) {

		int size = graph.getDatas().size();
		for (int i = 0; i < size; i++) {
			graph.getColours().add(getColor(i));
		}

	}

	//see http://www.colourlovers.com/palette/932683/Compatible
	private static final String COLOR_1 = "#7FC7AF";
	private static final String COLOR_2 = "#DAD8A7";
	private static final String COLOR_3 = "#FF9E9D";
	private static final String COLOR_4 = "#FF3D7F";
	private static final String COLOR_5 = "#3FB8AF";

	private static String[] COLORS = { COLOR_1, COLOR_2, COLOR_3, COLOR_4,
			COLOR_5 };

	private String getColor(int i) {
		return COLORS[i % COLORS.length];
	}

}
