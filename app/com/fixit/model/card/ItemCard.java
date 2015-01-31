package com.fixit.model.card;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fixit.model.Card;
import com.fixit.model.Contributable;
import com.fixit.model.Contribution;
import com.fixit.model.ContributionHolder;

public class ItemCard extends Card implements Contributable<ItemContribution> {

	public static final String TYPE = "item";
	
	public ItemCard() {
		this.type = TYPE;
	}

	private final ContributionHolder<ItemContribution> contributions = new ContributionHolder<ItemContribution>();

	private String name;
	private boolean limited = false;
	private int required = 1;
	private int provided;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRequired() {
		return required;
	}

	public void setRequired(int required) {
		this.required = required;
	}

	public int getProvided() {
		return provided;
	}

	public void setProvided(int provided) {
		this.provided = provided;
	}

	public boolean isLimited() {
		return limited;
	}

	public void setLimited(boolean limited) {
		this.limited = limited;
	}

	@JsonIgnore
	public int getRemaining() {
		if (required >= provided) {
			return required - provided;
		} else {
			return 0;
		}
	}

	@JsonIgnore
	public int calculateProvided() {
		int result = 0;

		List<ItemContribution> validContributions = contributions
				.getValidContributions();
		for (ItemContribution contribution : validContributions) {
			result += contribution.getQuantityProvided();
		}
		return result;
	}

	public void provide(String username, int quantity) {
		int remaining = getRemaining();
		if (remaining > 0) {
			ItemContribution contribution = new ItemContribution();
			contribution.setDate(new Date());

			contribution.setContributor(username);
			if (quantity <= remaining) {
				contribution.setQuantityProvided(quantity);
			} else {
				contribution.setQuantityProvided(remaining);
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
	public List<ItemContribution> getContributions() {
		return contributions.getContributions();
	}

	@Override
	public int getContributionSize() {
		return contributions.getValidContributions().size();
	}

	@Override
	public void setContributions(List<ItemContribution> contributions) {
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
