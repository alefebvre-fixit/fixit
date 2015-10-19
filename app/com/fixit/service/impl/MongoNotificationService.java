package com.fixit.service.impl;

import java.util.List;
import java.util.Set;

import com.fixit.model.notification.Notification;
import com.fixit.service.NotificationService;
import com.fixit.service.impl.subscription.MongoSubscriptionService;

public class MongoNotificationService implements NotificationService {

	public static final String USERNAME = "username";

/*	private JacksonDBCollection<Notification, String> getNotificationsCollection() {
		return MongoDBPersistence.getNotificationsCollection();
	}*/

	@Override
	public Notification getNotification(String notificationId) {
		return null;
/*		return getNotificationsCollection().findOneById(notificationId);
*/	}

	@Override
	public Notification saveNotification(Notification notification) {
		return null;
/*		WriteResult<Notification, String> result = null;
		if (notification.getId() == null) {
			result = getNotificationsCollection().insert(notification);
			notification.setId(result.getSavedId());
		} else {
			result = getNotificationsCollection().updateById(
					notification.getId(), notification);
		}
		return notification;*/
	}

	@Override
	public void deleteNotification(String notificationId) {
/*		getNotificationsCollection().removeById(notificationId);
*/	}

	@Override
	public List<Notification> getUserNotifications(String username, int offset,
			int length) {
		return null;
/*		DBCursor<Notification> cursor = getNotificationsCollection().find().is(
				USERNAME, username);
		if (offset > 0) {
			cursor.skip(offset);
		}
		if (length > 0) {
			cursor.limit(length);
		}
		return cursor.toArray(MongoDBPersistence.MAX_OBJECT);*/
	}

	@Override
	public void publishNotification(Object o) {
/*		Notification notification = NotificationFactory.getInstance(o)
				.createNotification(o);
		if (notification != null) {
			Set<String> subscribers = getSubscribers(notification);
			if (subscribers != null && subscribers.size() > 0) {
				List<Notification> notifications = new ArrayList<Notification>(
						subscribers.size());
				try {
					for (String subscriber : subscribers) {
						Notification cloned = (Notification) notification
								.clone();
						cloned.setUsername(subscriber);
						notifications.add(cloned);
					}
				} catch (CloneNotSupportedException e) {
					Logger.error(e.getMessage());
				}
				getNotificationsCollection().insert(notifications);
			}
		}*/
	}

	private Set<String> getSubscribers(Notification notification) {
		return MongoSubscriptionService.getInstance(notification).getSubscribers(notification);
	}

}
