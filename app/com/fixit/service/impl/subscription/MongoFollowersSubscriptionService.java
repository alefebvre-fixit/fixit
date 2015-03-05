package com.fixit.service.impl.subscription;

import java.util.HashSet;
import java.util.Set;

import com.fixit.model.notification.Notification;
import com.fixit.service.SubscriptionService;

public class MongoFollowersSubscriptionService extends MongoSubscriptionService
		implements SubscriptionService {

	@Override
	public Set<String> getSubscribers(Notification notification) {

		Set<String> result = new HashSet<String>();

		if (Notification.TYPE_FOLLOWERS.equals(notification.getType())) {

		}
		// TODO To be implemented

		return result;
	}

}
