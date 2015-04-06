package com.fixit.model.card.advice;

import java.util.List;

import com.fixit.model.Contribution;

public class AdviceLikeContribution extends Contribution {

	public static final String TYPE = "AdviceLike";

	public AdviceLikeContribution() {
		this.type = TYPE;
	}

	private boolean like = true;
	private String adviceId;

	@Override
	public boolean merge(List<Contribution> contributions) {
		// We do not allow merge for advice
		return false;
	}

	public boolean isLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

	public String getAdviceId() {
		return adviceId;
	}

	public void setAdviceId(String adviceId) {
		this.adviceId = adviceId;
	}

}
