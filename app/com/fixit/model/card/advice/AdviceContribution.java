package com.fixit.model.card.advice;

import java.util.List;

import com.fixit.model.Contribution;

public class AdviceContribution extends Contribution {

	public static final String TYPE = AdviceCard.TYPE;

	public AdviceContribution() {
		this.type = TYPE;
	}

	private String adviceId;
	private String content;

	public String getAdviceId() {
		return adviceId;
	}

	public void setAdviceId(String adviceId) {
		this.adviceId = adviceId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public boolean merge(List<Contribution> contributions) {
		// We do not allow merge for advice
		return false;
	}

}
