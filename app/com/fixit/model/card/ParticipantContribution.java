package com.fixit.model.card;

import com.fixit.model.Contribution;

public class ParticipantContribution extends Contribution {
	
	public static final String TYPE = "participant";

	public ParticipantContribution() {
		this.type = TYPE;
	}

	private int participants;

	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}



}
