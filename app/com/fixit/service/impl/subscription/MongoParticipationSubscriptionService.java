package com.fixit.service.impl.subscription;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Named;

import play.Logger;

import com.fixit.model.notification.Notification;
import com.fixit.service.SubscriptionService;

@Named
public class MongoParticipationSubscriptionService extends
		MongoSubscriptionService implements SubscriptionService {

	@Override
	public Set<String> getSubscribers(Notification notification) {

		Logger.debug("MongoParticipationSubscriptionService.getSubscribers(Notification notification");
		
		Set<String> result = new HashSet<String>();

		if (notification.getEventId() != null) {

			// Owner of the event want to receive notifications
			String owner = getEventService().getEventOwner(
					notification.getEventId());

			Logger.debug("MongoParticipationSubscriptionService.getSubscribers(Notification notification) owner="
					+ owner);

			if (owner != null) {
				result.add(owner);
			}

		}

		return result;
	}

}
