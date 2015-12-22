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
	public Notification findOne(String notificationId) {
		return notificationRepository.findOne(notificationId);
	}

	@Override
	public Notification save(Notification notification) {
		return notificationRepository.save(notification);
	}

	@Override
	public void delete(String notificationId) {
		notificationRepository.delete(notificationId);
	}

	@Override
	public List<Notification> findUserNotifications(String username, int offset,
			int length) {
		List<Notification> result = null;
		if (length > 0) {
			Page<Notification> pages = notificationRepository.findByUsernameOrderByNotificationDateDesc(
					username, new PageRequest(offset, length));
			result = pages.getContent();
		} else {
			result = notificationRepository.findByUsernameOrderByNotificationDateDesc(username);
		}
		return result;
	}

	@Override
	public void publishNotification(Object o) {

		Notification notification = NotificationFactory.getInstance(o)
				.createNotification(o);
		if (notification != null) {
			Set<String> subscribers = findSubscribers(notification);
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

	private Set<String> findSubscribers(Notification notification) {
		return serviceFactory.getInstance(notification).getSubscribers(
				notification);
	}

	@Override
	public List<Notification> findGroupNotifications(String groupId,
			String username) {
		return notificationRepository.findByUsernameAndGroupId(username, groupId);
	}

	@Override
	public List<Notification> findEventNotifications(String eventId,
			String username) {
		return notificationRepository.findByUsernameAndEventId(username, eventId);
	}

	@Override
	public void acknowledgeEventNotifications(String eventId, String username) {
		List<Notification> notifications = findEventNotifications(eventId, username);
		if (notifications != null && notifications.size()>0){
			deleteNotifications(notifications);
		}
	}

	@Override
	public void acknowledgeGroupNotifications(String groupId, String username) {
		List<Notification> notifications = findGroupNotifications(groupId, username);
		if (notifications != null && notifications.size()>0){
			deleteNotifications(notifications);
		}
	}

	@Override
	public void acknowledgeNotification(String notificationId) {
		delete(notificationId);
	}

	@Override
	public void deleteNotifications(List<Notification> notifications) {
		notificationRepository.delete(notifications);
	}

	@Override
	public void acknowledgeNotifications(String username) {
		List<Notification> notifications = notificationRepository.findByUsername(username);
		if (notifications != null && notifications.size()>0){
			deleteNotifications(notifications);
		}		
	}

}
