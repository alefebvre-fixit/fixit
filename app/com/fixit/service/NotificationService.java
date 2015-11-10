package com.fixit.service;

import java.util.List;

import com.fixit.model.notification.Notification;

public interface NotificationService {
	
	public Notification getNotification(String notificationId);

	public Notification saveNotification(Notification notification);

	public void deleteNotification(String notificationId);

	public void deleteNotifications(List<Notification> notifications);

	public List<Notification> getUserNotifications(String username, int offset,
			int length);
	
	public void publishNotification(Object o);
	
	public List<Notification> getGroupNotifications(String groupId, String username);
	
	public List<Notification> getEventNotifications(String eventId, String username);
	
	public void acknowledgeEventNotifications(String eventId, String username);

	public void acknowledgeGroupNotifications(String groupId, String username);

	public void acknowledgeNotification(String notificationId);
	
	public void acknowledgeNotifications(String username);


}
