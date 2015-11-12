package com.fixit.model.notification.impl;

import java.util.Date;

import play.Logger;

import com.fixit.model.event.Event;
import com.fixit.model.notification.Notification;
import com.fixit.model.notification.NotificationFactory;

public class EventNotificationFactory extends NotificationFactory {

	@Override
	public Notification createNotification(Object object) {
		if (object instanceof Event) {
			Event event = (Event) object;
			Notification notification = new Notification();

			notification.setType(Notification.TYPE_EVENT);
			notification.setActor(event.getUsername());
			notification.setGroupId(event.getGroupId());
			notification.setEventName(event.getName());
			notification.setGroupName(event.getGroupName());
			notification.setEventId(event.getId());
			notification.setNotificationDate(new Date());

			notification.setStatus(event.getStatus());

			return notification;
		} else {
			Logger.debug("Object " + object.getClass().getName()
					+ " is not an instance of " + Event.class.getSimpleName());
			return null;
		}
	}

}