package com.fixit.model.card;

import com.fixit.model.Card;

public class CardFactory {
	
	
	public static final Card createCard(String type){
		
		Card result = null;
		
		if (ItemCard.TYPE.equals(type)){
			result = new ItemCard();
		}
		else if (DateCard.TYPE.equals(type)){
			result = new DateCard();
		}
		
		
		return result;
	}

}
