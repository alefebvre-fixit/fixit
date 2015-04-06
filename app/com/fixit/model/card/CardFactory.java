package com.fixit.model.card;

import com.fixit.model.Card;
import com.fixit.model.card.advice.AdviceCard;
import com.fixit.model.card.availability.AvailabilityCard;
import com.fixit.model.card.item.ItemCard;
import com.fixit.model.card.money.MoneyCard;
import com.fixit.model.card.participant.ParticipantCard;
import com.fixit.model.card.survey.SurveyCard;

public class CardFactory {
	
	public static final Card createCard(String projectId, String type, String username){
		
		Card result = null;
		
		if (ItemCard.TYPE.equals(type)){
			result = new ItemCard();
		}
		else if (AvailabilityCard.TYPE.equals(type)){
			result = new AvailabilityCard();
		}
		else if (ParticipantCard.TYPE.equals(type)){
			result = new ParticipantCard();
		}
		else if (SurveyCard.TYPE.equals(type)){
			result = new SurveyCard();
		}
		else if (MoneyCard.TYPE.equals(type)){
			result = new MoneyCard();
		}
		else if (AdviceCard.TYPE.equals(type)){
			result = new AdviceCard();
		}
		
		if (result != null){
			result.setProjectId(projectId);
			result.setStatus(Card.STATUS_NEW);
			result.setUsername(username);
		}
		
		return result;
	}

}
