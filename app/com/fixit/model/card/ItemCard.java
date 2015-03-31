package com.fixit.model.card;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fixit.model.Card;
import com.fixit.model.Contribution;

public class ItemCard extends Card {

	public static final String TYPE = "item";

	public ItemCard() {
		this.type = TYPE;
	}

	private String name;

	private boolean isMinimum = false;
	private int minimumItem = 0;

	private boolean isMaximum = false;
	private int maximumItem = 0;

	private int increment = 1;
	
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
	
	public int getIncrement() {
		return increment;
	}

	public void setIncrement(int increment) {
		this.increment = increment;
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
	public boolean cancel(Contribution contribution) {
		if (contribution instanceof ItemContribution){
			ItemContribution itemContribution = (ItemContribution) contribution;
			itemContribution.setStatus(Contribution.STATUS_CANCELED);
			items -= itemContribution.getQuantityProvided();
			decrementContributions();
			return true;
		}
		return false;
	}

	@Override
	public boolean contribute(Contribution contribution, List<Contribution> contributions) {
		if (contribution instanceof ItemContribution){
			
			if (!isOpenForContribution(contributions)){
				return false;
			}
			
			ItemContribution itemContribution = (ItemContribution) contribution;
			if (!isMaximum() || getRemaining() >= itemContribution.getQuantityProvided()){		
				items += itemContribution.getQuantityProvided();
				if (!contribution.merge(contributions)){
					incrementContributions();					
				}
				return true;
			} 
		}
		return false;
	}

	@Override
	public boolean isOpenForContribution(List<Contribution> contributions) {
		
		if (isMaximum() && getRemaining() <= 0){
			return false;
		}
		
		if (contributions != null && contributions.size() > 0){
			return false;
		}
		
		return true;
	}
	
}
