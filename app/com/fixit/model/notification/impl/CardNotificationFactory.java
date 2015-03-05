package com.fixit.model.notification.impl;

import java.util.Date;

import play.Logger;

import com.fixit.model.Card;
import com.fixit.model.notification.Notification;
import com.fixit.model.notification.NotificationFactory;

public class CardNotificationFactory extends NotificationFactory {

	@Override
	public Notification createNotification(Object object) {
		if (object instanceof Card) {
			Card card = (Card) object;
			Notification notification = new Notification();

			notification.setType(Notification.TYPE_CONTRIBUTION);
			notification.setActor(card.getUsername());
			notification.setProjectId(card.getProjectId());
			notification.setCardId(card.getId());
			notification.setNotificationDate(new Date());

			notification.setStatus(card.getStatus());
			notification.setSubtype(card.getType());

			return notification;
		} else {
			Logger.debug("Object " + object.getClass().getName()
					+ " is not an instance of " + Card.class.getSimpleName());
			return null;
		}
	}

}
