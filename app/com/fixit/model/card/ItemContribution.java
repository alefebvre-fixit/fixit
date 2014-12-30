package com.fixit.model.card;

import com.fixit.model.Contribution;

public class ItemContribution extends Contribution {
	
	public static final String TYPE = "item";

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

}
