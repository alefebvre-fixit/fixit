package com.fixit.model.card.item;

import java.util.List;

import com.fixit.model.Contribution;

public class ItemContribution extends Contribution {
	
	public static final String TYPE = ItemCard.TYPE;

	public ItemContribution() {
		this.type = TYPE;
	}

	private int quantityProvided;

	public int getQuantityProvided() {
		return quantityProvided;
	}

	public void setQuantityProvided(int quantityProvided) {
		this.quantityProvided = quantityProvided;
	}

	@Override
	public boolean merge(List<Contribution> contributions) {
		if (contributions != null && contributions.size() > 0){
			ItemContribution itemContribution = (ItemContribution) contributions.get(0);
			quantityProvided += itemContribution.getQuantityProvided();
			setStatus(STATUS_UPDATED);
			this.id = itemContribution.id;
			return true;
		}
		return false;
	}
	
}
