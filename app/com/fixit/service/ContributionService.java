package com.fixit.service;

import java.util.List;

import com.fixit.model.Contribution;

public interface ContributionService {

	public Contribution getContribution(String contributionId);

	public Contribution saveContribution(Contribution contribution);

	public void deleteContribution(String id);

	public List<Contribution> getUserContributions(String username, int offset,
			int length);

	public List<Contribution> getProjectContributions(String username,
			String projectId);

	public List<Contribution> getProjectContributions(String projectId);

	public List<Contribution> getCardContributions(String cardId);

	public int countContributionsByOwner(String username);

	public int countUserContributionForCard(String username, String cardId);

	public List<Contribution> getUserContributionForCard(String username,
			String cardId);

	public List<Contribution> getUserContributionForCard(String username,
			String cardId, int offset, int length);

	public int countOtherContributionForCard(String username, String cardId);

	public List<Contribution> getOtherContributionForCard(String username,
			String cardId, int offset, int length);

}
