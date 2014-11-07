package com.fixit.model.card;

import com.fixit.model.Card;
import com.fixit.model.Contribution;

public class DateCard extends Card {

	public DateCard() {
		this.type = "date";
	}

	@Override
	public boolean cancel(Contribution contribution) {
		contribution.setStatus(Contribution.STATUS_CANCELED);
		return true;
	}
}
