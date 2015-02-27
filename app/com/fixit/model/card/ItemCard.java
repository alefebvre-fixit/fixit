package com.fixit.model.card;

import java.util.Date;
import java.util.List;

import play.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fixit.model.Card;
import com.fixit.model.Contribution;

public class ItemCard extends Card<ItemContribution> {

	public static final String TYPE = "item";

	public ItemCard() {
		this.type = TYPE;
	}

	private String name;

	private boolean isMinimum = false;
	private int minimumItem = 0;

	private boolean isMaximum = false;
	private int maximumItem = 0;

	private int items = 0;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isMinimum() {
		return isMinimum;
	}

	public void setMinimum(boolean isMinimum) {
		this.isMinimum = isMinimum;
	}

	public int getMinimumItem() {
		return minimumItem;
	}

	public void setMinimumItem(int minimumItem) {
		this.minimumItem = minimumItem;
	}

	public boolean isMaximum() {
		return isMaximum;
	}

	public void setMaximum(boolean isMaximum) {
		this.isMaximum = isMaximum;
	}

	public int getMaximumItem() {
		return maximumItem;
	}

	public void setMaximumItem(int maximumItem) {
		this.maximumItem = maximumItem;
	}

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	@JsonIgnore
	public int getRemaining() {

		if (!isMaximum) {
			return -1;
		}

		if (maximumItem >= items) {
			return maximumItem - items;
		} else {
			return 0;
		}

	}

	@JsonIgnore
	public int getRequired() {

		if (!isMinimum) {
			return -1;
		}

		if (minimumItem >= items) {
			return minimumItem - items;
		} else {
			return 0;
		}

	}

	@JsonIgnore
	public int calculateItemNumber(List<ItemContribution> contributions) {
		int result = 0;

		for (ItemContribution contribution : contributions) {
			result += contribution.getQuantityProvided();
		}
		return result;
	}

	@Override
	public boolean cancel(ItemContribution contribution) {

		contribution.setStatus(Contribution.STATUS_CANCELED);
		items -= contribution.getQuantityProvided();
		decrementContributions();

		return true;
	}

	private boolean contribute(ItemContribution contribution) {
		Logger.debug("contribute + isMaximum()" + isMaximum()  
				+ " getRemaining()=" + getRemaining() + "getQuantityProvided()" + contribution.getQuantityProvided());
		
		if (!isMaximum() || getRemaining() >= contribution.getQuantityProvided()){
			items += contribution.getQuantityProvided();
			incrementContributions();
			return true;
		} 

		return false;
	}

	public ItemContribution provide(String username, int items) {
		ItemContribution contribution = new ItemContribution();

		contribution.setDate(new Date());
		contribution.setContributor(username);
		contribution.setQuantityProvided(items);

		if (contribute(contribution)) {
			return contribution;
		} else {
			return null;
		}
	}

}
