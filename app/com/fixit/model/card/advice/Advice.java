package com.fixit.model.card.advice;

import java.util.Date;
import java.util.List;

import com.fixit.model.Contribution;

public class Advice {

	private String id;

	private Date adviceDate;

	private int likeNumber = 0;
	private int dislikeNumber = 0;

	private String content = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLikeNumber() {
		return likeNumber;
	}

	public void setLikeNumber(int likeNumber) {
		this.likeNumber = likeNumber;
	}

	public int getDislikeNumber() {
		return dislikeNumber;
	}

	public void setDislikeNumber(int dislikeNumber) {
		this.dislikeNumber = dislikeNumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getAdviceDate() {
		return adviceDate;
	}

	public void setAdviceDate(Date adviceDate) {
		this.adviceDate = adviceDate;
	}

	public boolean cancel(AdviceLikeContribution contribution) {
		boolean result = true;

		if (contribution.isLike()) {
			likeNumber--;
		} else {
			dislikeNumber--;
		}

		return result;
	}

	public boolean contribute(AdviceLikeContribution contribution,
			List<Contribution> contributions) {

		if (isOpenForContribution(contributions)) {
			if (contribution.isLike()) {
				likeNumber++;
			} else {
				dislikeNumber++;
			}
			return true;
		}
		return false;
	}

	public boolean isOpenForContribution(List<Contribution> contributions) {

		if (contributions != null && contributions.size() > 0) {
			for (Contribution contribution : contributions) {
				if (contribution instanceof AdviceLikeContribution) {
					AdviceLikeContribution like = (AdviceLikeContribution) contribution;
					if (like.getAdviceId().equals(getId())) {
						return false;
					}
				}
			}
		}

		return true;
	}

}
