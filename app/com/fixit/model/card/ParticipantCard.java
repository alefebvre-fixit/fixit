package com.fixit.model.card;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fixit.model.Card;
import com.fixit.model.Contribution;

public class ParticipantCard extends Card<ParticipantContribution> {

	public static final String TYPE = "participant";

	public ParticipantCard() {
		this.type = TYPE;
	}

	private String name;

	private boolean isMinimum = false;
	private int minimumParticipant = 0;

	private boolean isMaximum = false;
	private int maximumParticipant = 0;

	private boolean plusAllowed = true;
	private boolean isPlusMaximum = false;
	private int plusMaximumParticipant = 4;

	private int participants = 0;

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

		if (isMaximum) {
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

		if (isMinimum) {
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
	public boolean cancel(ParticipantContribution contribution) {

		contribution.setStatus(Contribution.STATUS_CANCELED);
		participants -= contribution.getParticipants();
		decrementContributions();

		return true;
	}

	private boolean contribute(ParticipantContribution contribution) {

		if (isMaximum() && (getRemaining() >= contribution.getParticipants())) {
			participants += contribution.getParticipants();
			incrementContributions();
			return true;
		}

		return false;
	}

	public ParticipantContribution participate(String username, int participants) {
		ParticipantContribution contribution = new ParticipantContribution();

		contribution = new ParticipantContribution();
		contribution.setDate(new Date());
		contribution.setContributor(username);
		contribution.setParticipants(participants);

		if (contribute(contribution)) {
			return contribution;
		} else {
			return null;
		}
	}

}
