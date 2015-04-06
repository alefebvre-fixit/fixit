package com.fixit.model.card.participant;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fixit.model.Card;
import com.fixit.model.Contribution;

public class ParticipantCard extends Card {

	public static final String TYPE = "participant";

	public ParticipantCard() {
		this.type = TYPE;
	}

	private boolean isMinimum = false;
	private int minimumParticipant = 0;

	private boolean isMaximum = false;
	private int maximumParticipant = 0;

	private boolean plusAllowed = true;
	private boolean isPlusMaximum = false;
	private int plusMaximumParticipant = 4;

	private int participants = 0;

	public boolean isMinimum() {
		return isMinimum;
	}

	public void setMinimum(boolean isMinimum) {
		this.isMinimum = isMinimum;
	}

	public int getMinimumParticipant() {
		return minimumParticipant;
	}

	public void setMinimumParticipant(int minimumParticipant) {
		this.minimumParticipant = minimumParticipant;
	}

	public boolean isMaximum() {
		return isMaximum;
	}

	public void setMaximum(boolean isMaximum) {
		this.isMaximum = isMaximum;
	}

	public int getMaximumParticipant() {
		return maximumParticipant;
	}

	public void setMaximumParticipant(int maximumParticipant) {
		this.maximumParticipant = maximumParticipant;
	}

	public boolean isPlusAllowed() {
		return plusAllowed;
	}

	public void setPlusAllowed(boolean plusAllowed) {
		this.plusAllowed = plusAllowed;
	}

	public boolean isPlusMaximum() {
		return isPlusMaximum;
	}

	public void setPlusMaximum(boolean isPlusMaximum) {
		this.isPlusMaximum = isPlusMaximum;
	}

	public int getPlusMaximumParticipant() {
		return plusMaximumParticipant;
	}

	public void setPlusMaximumParticipant(int plusMaximumParticipant) {
		this.plusMaximumParticipant = plusMaximumParticipant;
	}

	public int getParticipants() {
		return participants;
	}

	public void setParticipants(int participants) {
		this.participants = participants;
	}

	@JsonIgnore
	public int getRemaining() {

		if (!isMaximum) {
			return -1;
		}

		if (maximumParticipant >= participants) {
			return maximumParticipant - participants;
		} else {
			return 0;
		}

	}

	@JsonIgnore
	public int getRequired() {

		if (!isMinimum) {
			return -1;
		}

		if (minimumParticipant >= participants) {
			return minimumParticipant - participants;
		} else {
			return 0;
		}

	}

	@JsonIgnore
	public int calculateParticipantNumber(
			List<ParticipantContribution> contributions) {
		int result = 0;

		for (ParticipantContribution contribution : contributions) {
			result += contribution.getParticipants();
		}
		return result;
	}

	@Override
	public boolean cancel(Contribution contribution) {
		if (contribution instanceof ParticipantContribution) {
			ParticipantContribution participantContribution = (ParticipantContribution) contribution;
			participantContribution.setStatus(Contribution.STATUS_CANCELED);
			participants -= participantContribution.getParticipants();
			decrementContributions();
			return true;
		}
		return false;
	}

	@Override
	public boolean contribute(Contribution contribution, List<Contribution> contributions) {
		if (contribution instanceof ParticipantContribution) {
			
			if (!isOpenForContribution(contributions)){
				return false;
			}
			
			ParticipantContribution participantContribution = (ParticipantContribution) contribution;
			if (!isMaximum()
					|| (getRemaining() >= participantContribution
							.getParticipants())) {
				participants += participantContribution.getParticipants();
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
