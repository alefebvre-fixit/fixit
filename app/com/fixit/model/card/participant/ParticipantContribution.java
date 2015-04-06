package com.fixit.model.card.participant;

import java.util.List;

import com.fixit.model.Contribution;

public class ParticipantContribution extends Contribution {
	
	public static final String TYPE = "participant";

	public ParticipantContribution() {
		this.type = TYPE;
	}

	private int participants;
	private boolean participate;
	
	public boolean isParticipate() {
		return participate;
	}

	public void setParticipate(boolean participate) {
		this.participate = participate;
	}

	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}

	@Override
	public boolean merge(List<Contribution> contributions) {
		if (contributions != null && contributions.size() > 0){
			ParticipantContribution participantContribution = (ParticipantContribution) contributions.get(0);
			participants += participantContribution.getParticipants();
			setStatus(STATUS_UPDATED);
			this.id = participantContribution.id;
			return true;
		}
		return false;
	}
	
}
