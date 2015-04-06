package com.fixit.model.card.advice;

import java.util.ArrayList;
import java.util.List;

import com.fixit.model.Card;
import com.fixit.model.Contribution;

public class AdviceCard extends Card {

	public static final String TYPE = "advice";

	public AdviceCard() {
		this.type = TYPE;
	}

	private List<Advice> advices = new ArrayList<Advice>();

	public List<Advice> getAdvices() {
		return advices;
	}

	public void setAdvices(List<Advice> advices) {
		this.advices = advices;
	}

	public Advice getAdvice(String adviceId) {

		if (adviceId == null) {
			return null;
		}

		for (Advice advice : advices) {
			if (adviceId.equals(advice.getId())) {
				return advice;
			}
		}

		return null;
	}

	@Override
	public boolean cancel(Contribution contribution) {
		boolean result = false;
		if (contribution instanceof AdviceContribution) {
			AdviceContribution adviceContribution = (AdviceContribution) contribution;
			Advice advice = getAdvice(adviceContribution.getAdviceId());
			if (advice != null) {
				decrementContributions();
				advices.remove(advice);
			}
			return true;
		} else if (contribution instanceof AdviceLikeContribution) {
			AdviceLikeContribution likeContribution = (AdviceLikeContribution) contribution;
			Advice advice = getAdvice(likeContribution.getAdviceId());
			if (advice != null) {
				advice.cancel(likeContribution);
			}
		}

		return result;
	}

	@Override
	public boolean contribute(Contribution contribution,
			List<Contribution> contributions) {

		if (!isOpenForContribution(contributions)) {
			return false;
		}

		if (contribution instanceof AdviceContribution) {
			AdviceContribution adviceContribution = (AdviceContribution) contribution;
			Advice advice = AdviceFactory.create(adviceContribution);
			adviceContribution.setAdviceId(advice.getId());
			advices.add(advice);
			incrementContributions();
			return true;
		} else if (contribution instanceof AdviceLikeContribution) {
			AdviceLikeContribution likeContribution = (AdviceLikeContribution) contribution;
			Advice advice = getAdvice(likeContribution.getAdviceId());
			if (advice != null) {
				advice.contribute(likeContribution, contributions);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean isOpenForContribution(List<Contribution> contributions) {

		if (contributions != null && contributions.size() > 0) {
			return false;
		}

		return true;
	}

}
