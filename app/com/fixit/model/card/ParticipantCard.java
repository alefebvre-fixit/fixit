package com.fixit.model.card;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fixit.model.Card;
import com.fixit.model.Contributable;
import com.fixit.model.Contribution;
import com.fixit.model.ContributionHolder;

public class ParticipantCard extends Card implements Contributable<ParticipantContribution> {

	public static final String TYPE = "participant";
	
	public ParticipantCard() {
		this.type = TYPE;
	}

	private final ContributionHolder<ParticipantContribution> contributions = new ContributionHolder<ParticipantContribution>();

	private String name;
	
	private boolean required = false;
	private int requirement = 0;
	
	private boolean limited = false;
	private int limit = 0;
	
	private int provided;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProvided() {
		return provided;
	}

	public void setProvided(int provided) {
		this.provided = provided;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public int getRequirement() {
		return requirement;
	}

	public void setRequirement(int requirement) {
		this.requirement = requirement;
	}

	public boolean isLimited() {
		return limited;
	}

	public void setLimited(boolean limited) {
		this.limited = limited;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@JsonIgnore
	public int getRemaining() {
		if (limit >= provided) {
			return limit - provided;
		} else {
			return 0;
		}
	}

	@JsonIgnore
	public int calculateProvided() {
		int result = 0;

		List<ParticipantContribution> validContributions = contributions
				.getValidContributions();
		for (ParticipantContribution contribution : validContributions) {
			result += contribution.getParticipantNumber();
		}
		return result;
	}

	public void provide(String username, int quantity) {
		int remaining = getRemaining();
		if (remaining > 0) {
			ParticipantContribution contribution = new ParticipantContribution();
			contribution.setDate(new Date());

			contribution.setContributor(username);
			if (quantity <= remaining) {
				contribution.setParticipantNumber(quantity);
			} else {
				contribution.setParticipantNumber(remaining);
			}
			contributions.add(contribution);
			provided = calculateProvided();
		}
	}
	
	@Override
	public boolean cancel(String contributionId) {
		Contribution contribution = getContribution(contributionId);
		if (contribution != null){
			contribution.setStatus(Contribution.STATUS_CANCELED);
			provided = calculateProvided();
			return true;
		}
		return false;
	}

	@Override
	public List<ParticipantContribution> getContributions() {
		return contributions.getContributions();
	}

	@Override
	public int getContributionSize() {
		return contributions.getValidContributions().size();
	}

	@Override
	public void setContributions(List<ParticipantContribution> contributions) {
		this.contributions.setContributions(contributions);
	}

	@Override
	public Contribution getContribution(String contributionId) {
		return contributions.getContribution(contributionId);
	}

	@Override
	public List<Contributable<? extends Contribution>> getContributables() {
		List<Contributable<? extends Contribution>> result = new ArrayList<Contributable<? extends Contribution>>();
		result.add(this);
		return result;
	}

}
