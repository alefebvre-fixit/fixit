package com.fixit.service;

import com.fixit.model.Card;
import com.fixit.model.card.CardSummary;

public interface CardService {
	
	public CardSummary getCardSummary(String username, String projectId, String cardId);
	
	public Card getCard(String projectId, String cardId);

}
