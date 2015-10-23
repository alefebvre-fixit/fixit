package com.fixit.service.impl.subscription;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Named;

import com.fixit.model.notification.Notification;
import com.fixit.service.SubscriptionService;

@Named
public class MongoFavoriteSubscriptionService extends MongoSubscriptionService
		implements SubscriptionService {

	@Override
	public Set<String> getSubscribers(Notification notification) {

		Set<String> result = new HashSet<String>();

		if (Notification.TYPE_FAVORITE.equals(notification.getType())) {
			// Owner of the project want to receive notifications
			String owner = getProjectService().getProjectOwner(
					notification.getProjectId());
			if (owner != null) {
				result.add(owner);
			}
		}

		return result;
	}

}
