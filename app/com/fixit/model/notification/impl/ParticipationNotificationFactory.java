package com.fixit.model.notification.impl;

import java.util.Date;

import play.Logger;

import com.fixit.model.event.Participation;
import com.fixit.model.notification.Notification;
import com.fixit.model.notification.NotificationFactory;

public class ParticipationNotificationFactory extends NotificationFactory {

	@Override
	public Notification createNotification(Object object) {
		if (object instanceof Participation) {
			Participation participation = (Participation) object;
			Notification notification = new Notification();

			notification.setType(Notification.TYPE_PARTICIPATION);
			notification.setActor(participation.getUsername());
			notification.setEventId(participation.getEventId());
			notification.setEventName(participation.getEventName());
			notification.setNotificationDate(new Date());
			notification.setStatus(participation.getStatus());

			return notification;
		} else {
			Logger.debug("Object " + object.getClass().getName()
					+ " is not an instance of "
					+ Participation.class.getSimpleName());
			return null;
		}
	}

}
