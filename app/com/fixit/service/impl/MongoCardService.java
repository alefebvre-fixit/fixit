package com.fixit.service.impl;

import com.fixit.model.Card;
import com.fixit.model.Project;
import com.fixit.model.card.CardSummary;
import com.fixit.model.card.graph.GraphDataFactory;
import com.fixit.service.CardService;
import com.fixit.service.ContributionService;
import com.fixit.service.ProjectService;

public class MongoCardService implements CardService {
		
	private ProjectService projectService;

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
	
	private ContributionService contributionService;

	public void setContributionService(ContributionService contributionService) {
		this.contributionService = contributionService;
	}

	public MongoCardService(ContributionService contributionService, ProjectService projectService){
		this.contributionService = contributionService;
		this.projectService = projectService;
	}
	
	@Override
	public CardSummary getCardSummary(String username, String projectId, String cardId) {		
		CardSummary summary = null;
		
		Card card = getCard(projectId, cardId);
		if (card != null){
			summary = new CardSummary(card);
		}
		
		summary.setUserContributionNumber(contributionService.countUserContributionForCard(username, cardId));
		summary.setLastUserContributions(contributionService.getUserContributionForCard(username, cardId, 0, 10));
		
		summary.setOtherContributionNumber(contributionService.countOtherContributionForCard(username, cardId));
		summary.setLastOtherContributions(contributionService.getOtherContributionForCard(username, cardId, 0, 10));
		
		summary.setOpenForContribution(card.isOpenForContribution(summary.getLastUserContributions()));
		
		summary.setGraphData(GraphDataFactory.getInstance(card).createGraphData(card));
		
		
		return summary;
	}

	@Override
	public Card getCard(String projectId, String cardId) {
		Card result = null;
		
		Project project = projectService.getProject(projectId);
		if (project != null){
			result = project.getCard(cardId);
		}
		
		return result;
	}

}
