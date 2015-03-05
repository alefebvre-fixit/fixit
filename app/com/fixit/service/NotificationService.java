package com.fixit.service;

import java.util.List;

import com.fixit.model.notification.Notification;

public interface NotificationService {

	public Notification getNotification(String notificationId);

	public Notification saveNotification(Notification notification);

	public void deleteNotification(String notificationId);

	public List<Notification> getUserNotifications(String username, int offset,
			int length);
	
	public void publishNotification(Object o);

}
