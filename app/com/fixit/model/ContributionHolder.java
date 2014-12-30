package com.fixit.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ContributionHolder<C extends Contribution> {

	public List<C> contributions = new ArrayList<C>();

	public List<C> getContributions() {
		return contributions;
	}

	@JsonIgnore
	public List<C> getValidContributions() {
		List<C> result = new ArrayList<C>();

		for (C contribution : contributions) {
			if (contribution.isValid()) {
				result.add(contribution);
			}
		}

		return result;
	}

	public int getContributionSize() {
		List<C> validContributions = getValidContributions();
		return validContributions.size();
	}

	public void setContributions(List<C> contributions) {
		this.contributions = contributions;
	}
	
	public void add(C contribution){
		this.contributions.add(contribution);
	}

	public C getContribution(String contributionId) {
		for (C contribution : contributions) {
			if (contribution.getId().equals(contributionId)) {
				return contribution;
			}
		}
		return null;
	}
	
	public C getContributionByOwner(String username) {
		for (C contribution : contributions) {
			if (contribution.getContributor().equals(username)) {
				return contribution;
			}
		}
		return null;
	}
	
	
	
}
