package com.fixit.model.notification.impl;

import java.util.Date;

import play.Logger;

import com.fixit.model.Contribution;
import com.fixit.model.notification.Notification;
import com.fixit.model.notification.NotificationFactory;

public class ContributionNotificationFactory extends NotificationFactory {

	@Override
	public Notification createNotification(Object object) {
		if (object instanceof Contribution) {
			Contribution contribution = (Contribution) object;
			Notification notification = new Notification();

			notification.setType(Notification.TYPE_CONTRIBUTION);
			notification.setActor(contribution.getContributor());
			notification.setProjectId(contribution.getProjectId());
			notification.setCardId(contribution.getCardId());
			notification.setNotificationDate(new Date());

			// TODO To be removed
			if (Contribution.STATUS_NEW.equals(contribution.getStatus())) {
				notification.setTitle("New contribution");
			} else if (Contribution.STATUS_UPDATED.equals(contribution
					.getStatus())) {
				notification.setTitle("Contribution updated");
			} else if (Contribution.STATUS_CANCELED.equals(contribution
					.getStatus())) {
				notification.setTitle("Contribution canceled");
			}

			notification.setStatus(contribution.getStatus());
			notification.setSubtype(contribution.getType());

			return notification;
		} else {
			Logger.debug("Object " + object.getClass().getName()
					+ " is not an instance of "
					+ Contribution.class.getSimpleName());
			return null;
		}
	}

}
