package com.fixit.model.card;

import com.fixit.model.Card;

public class CardFactory {
	
	
	public static final Card createCard(String projectId, String type, String username){
		
		Card result = null;
		
		if (ItemCard.TYPE.equals(type)){
			result = new ItemCard();
		}
		else if (DateCard.TYPE.equals(type)){
			result = new DateCard();
		}
		else if (ParticipantCard.TYPE.equals(type)){
			result = new ParticipantCard();
		}
		else if (SurveyCard.TYPE.equals(type)){
			result = new SurveyCard();
		}
		
		if (result != null){
			result.setProjectId(projectId);
			result.setStatus(Card.STATUS_NEW);
			result.setUsername(username);
		}
		
		return result;
	}

}
