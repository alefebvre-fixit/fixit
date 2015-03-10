package com.fixit.model.card;

import java.util.List;

import com.fixit.model.Card;
import com.fixit.model.Contribution;
import com.fixit.model.card.graph.GraphData;

public class CardSummary{
	
	private Card card;
	
	private int userContributionNumber = 0;
	private List<Contribution> lastUserContributions;
	
	private int otherContributionNumber = 0;
	private List<Contribution> lastOtherContributions;
	
	private boolean openForContribution = false;

	private GraphData graphData = null;
	
	public CardSummary(Card card){
		this.card = card;
	}

	public int getUserContributionNumber() {
		return userContributionNumber;
	}

	public void setUserContributionNumber(int userContributionNumber) {
		this.userContributionNumber = userContributionNumber;
	}

	public List<Contribution> getLastUserContributions() {
		return lastUserContributions;
	}

	public void setLastUserContributions(List<Contribution> lastUserContributions) {
		this.lastUserContributions = lastUserContributions;
	}

	public int getOtherContributionNumber() {
		return otherContributionNumber;
	}

	public void setOtherContributionNumber(int otherContributionNumber) {
		this.otherContributionNumber = otherContributionNumber;
	}

	public List<Contribution> getLastOtherContributions() {
		return lastOtherContributions;
	}

	public void setLastOtherContributions(List<Contribution> lastOtherContributions) {
		this.lastOtherContributions = lastOtherContributions;
	}

	public Card getCard() {
		return card;
	}

	public boolean isOpenForContribution() {
		return openForContribution;
	}

	public void setOpenForContribution(boolean openForContribution) {
		this.openForContribution = openForContribution;
	}

	public GraphData getGraphData() {
		return graphData;
	}

	public void setGraphData(GraphData graphData) {
		this.graphData = graphData;
	}
	
	
	
	
}
