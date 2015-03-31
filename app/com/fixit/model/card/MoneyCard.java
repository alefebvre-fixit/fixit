package com.fixit.model.card;

public class MoneyCard extends ItemCard {

	public static final String TYPE = "money";

	public MoneyCard() {
		this.type = TYPE;
	}
	
	private String currency = "USD";

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
}
