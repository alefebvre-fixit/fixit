package com.fixit.model.card;

import com.fixit.model.Contribution;

public class ItemContribution extends Contribution {

	public ItemContribution() {
		this.type = "item";
	}

	private int quantityProvided;

	public int getQuantityProvided() {
		return quantityProvided;
	}

	public void setQuantityProvided(int quantityProvided) {
		this.quantityProvided = quantityProvided;
	}

}
