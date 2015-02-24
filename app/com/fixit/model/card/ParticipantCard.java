package com.fixit.model.card;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fixit.model.Card;
import com.fixit.model.Contributable;
import com.fixit.model.Contribution;
import com.fixit.model.ContributionHolder;

public class ParticipantCard extends Card implements
		Contributable<ParticipantContribution> {

	public static final String TYPE = "participant";

	public ParticipantCard() {
		this.type = TYPE;
	}

	private final ContributionHolder<ParticipantContribution> contributions = new ContributionHolder<ParticipantContribution>();

	private String name;

	private boolean isMinimum = false;
	private int minimumParticipant = 0;

	private boolean isMaximum = false;
	private int maximumParticipant = 0;

	private boolean plusAllowed = true;
	private boolean isPlusMaximum = false;
	private int plusMaximumParticipant = 4;

	private int participantNumber = 0;

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

	public int getParticipantNumber() {
		return participantNumber;
	}

	public void setParticipantNumber(int participantNumber) {
		this.participantNumber = participantNumber;
	}

	@JsonIgnore
	public int getRemaining() {

		if (isMinimum) {
			return 0;
		}

		if (minimumParticipant >= participantNumber) {
			return minimumParticipant - participantNumber;
		} else {
			return 0;
		}

	}

	@JsonIgnore
	public int calculateParticipantNumber() {
		int result = 0;

		List<ParticipantContribution> validContributions = contributions
				.getValidContributions();
		for (ParticipantContribution contribution : validContributions) {
			result += contribution.getParticipantNumber();
		}
		return result;
	}

	public void participate(String username, int quantity) {
		int remaining = getRemaining();
		if (remaining > 0) {
			ParticipantContribution contribution = new ParticipantContribution();
			contribution.setDate(new Date());

			contribution.setContributor(username);
			if (quantity <= remaining) {
				contribution.setParticipantNumber(quantity);
			} else {
				contribution.setParticipantNumber(remaining);
			}
			contributions.add(contribution);
			participantNumber = calculateParticipantNumber();
		}
	}

	@Override
	public boolean cancel(String contributionId) {
		Contribution contribution = getContribution(contributionId);
		if (contribution != null) {
			contribution.setStatus(Contribution.STATUS_CANCELED);
			participantNumber = calculateParticipantNumber();
			return true;
		}
		return false;
	}

	@Override
	public List<ParticipantContribution> getContributions() {
		return contributions.getContributions();
	}

	@Override
	public int getContributionSize() {
		return contributions.getValidContributions().size();
	}

	@Override
	public void setContributions(List<ParticipantContribution> contributions) {
		this.contributions.setContributions(contributions);
	}

	@Override
	public Contribution getContribution(String contributionId) {
		return contributions.getContribution(contributionId);
	}

	@Override
	public List<Contributable<? extends Contribution>> getContributables() {
		List<Contributable<? extends Contribution>> result = new ArrayList<Contributable<? extends Contribution>>();
		result.add(this);
		return result;
	}

}
