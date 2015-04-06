package com.fixit.model.card.advice;

import java.util.Date;

public class AdviceFactory {

	public static Advice create(AdviceContribution contribution){
		Advice result = new Advice();
		result.setContent(contribution.getContent());
		result.setAdviceDate(new Date());
		result.setId(java.util.UUID.randomUUID().toString());
		return result;
	}
	
}
