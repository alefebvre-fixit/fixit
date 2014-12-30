package com.fixit.model.card;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fixit.model.Card;
import com.fixit.model.Contribution;

public class ItemCard extends Card<ItemContribution> {

	public ItemCard() {
		this.type = "item";
	}

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

		List<ItemContribution> validContributions = getValidContributions();
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
			contribute(contribution);
		}
	}

	public void contribute(ItemContribution contribution) {
		getContributions().add(contribution);
		provided = calculateProvided();
	}

	@Override
	public boolean cancel(Contribution contribution) {
		contribution.setStatus(Contribution.STATUS_CANCELED);
		provided = calculateProvided();
		return true;
	}

}
