package com.fixit.model.notification.impl;

import java.util.Date;

import play.Logger;

import com.fixit.model.Favorite;
import com.fixit.model.notification.Notification;
import com.fixit.model.notification.NotificationFactory;

public class FavoriteNotificationFactory extends NotificationFactory {

	@Override
	public Notification createNotification(Object object) {
		if (object instanceof Favorite) {
			Favorite favorite = (Favorite) object;
			Notification notification = new Notification();

			notification.setType(Notification.TYPE_FAVORITE);
			notification.setActor(favorite.getUsername());
			notification.setGroupId(favorite.getGroupId());
			notification.setNotificationDate(new Date());

			return notification;
		} else {
			Logger.debug("Object " + object.getClass().getName()
					+ " is not an instance of "
					+ Favorite.class.getSimpleName());
			return null;
		}
	}

}
