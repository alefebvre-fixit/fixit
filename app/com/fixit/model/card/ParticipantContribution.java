package com.fixit.model.card;

import com.fixit.model.Contribution;

public class ParticipantContribution extends Contribution {
	
	public static final String TYPE = "participant";

	public ParticipantContribution() {
		this.type = TYPE;
	}

	private int participantNumber;

	public int getParticipantNumber() {
		return participantNumber;
	}

	public void setParticipantNumber(int participantNumber) {
		this.participantNumber = participantNumber;
	}



}
