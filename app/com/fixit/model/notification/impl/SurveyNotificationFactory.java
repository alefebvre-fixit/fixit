package com.fixit.model.notification.impl;

import java.util.Date;

import play.Logger;

import com.fixit.model.notification.Notification;
import com.fixit.model.notification.NotificationFactory;
import com.fixit.model.survey.Survey;

public class SurveyNotificationFactory extends NotificationFactory {

	@Override
	public Notification createNotification(Object object) {
		if (object instanceof Survey) {
			Survey survey = (Survey) object;
			Notification notification = new Notification();

			notification.setType(Notification.TYPE_EVENT);
			notification.setActor(survey.getUsername());
			notification.setEventId(survey.getId());
			notification.setNotificationDate(new Date());

			notification.setStatus(survey.getStatus());

			return notification;
		} else {
			Logger.debug("Object " + object.getClass().getName()
					+ " is not an instance of " + Survey.class.getSimpleName());
			return null;
		}
	}

}
