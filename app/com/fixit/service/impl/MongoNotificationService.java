package com.fixit.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import play.Logger;

import com.fixit.dao.NotificationRepository;
import com.fixit.model.notification.Notification;
import com.fixit.model.notification.NotificationFactory;
import com.fixit.service.NotificationService;
import com.fixit.service.impl.subscription.SubscriptionServiceFactory;

@Named
public class MongoNotificationService implements NotificationService {

	public static final String USERNAME = "username";

	@Inject
	private NotificationRepository notificationRepository;

	@Inject
	private SubscriptionServiceFactory serviceFactory;

	@Override
	public Notification getNotification(String notificationId) {
		return notificationRepository.findOne(notificationId);
	}

	@Override
	public Notification saveNotification(Notification notification) {
		return notificationRepository.save(notification);
	}

	@Override
	public void deleteNotification(String notificationId) {
		notificationRepository.delete(notificationId);
	}

	@Override
	public List<Notification> getUserNotifications(String username, int offset,
			int length) {
		List<Notification> result = null;
		if (length > 0) {
			Page<Notification> pages = notificationRepository.findByUsername(
					username, new PageRequest(offset, length));
			result = pages.getContent();
		} else {
			result = notificationRepository.findByUsername(username);
		}
		return result;
	}

	@Override
	public void publishNotification(Object o) {

		Notification notification = NotificationFactory.getInstance(o)
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
				notificationRepository.save(notifications);
			}
		}

	}

	private Set<String> getSubscribers(Notification notification) {
		return serviceFactory.getInstance(notification).getSubscribers(
				notification);
	}

}
