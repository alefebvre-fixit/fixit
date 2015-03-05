package com.fixit.model.notification.impl;

import java.util.Date;

import play.Logger;

import com.fixit.model.Project;
import com.fixit.model.notification.Notification;
import com.fixit.model.notification.NotificationFactory;

public class ProjectNotificationFactory extends NotificationFactory {

	@Override
	public Notification createNotification(Object object) {
		if (object instanceof Project) {
			Project project = (Project) object;
			Notification notification = new Notification();

			notification.setType(Notification.TYPE_PROJECT);
			notification.setActor(project.getUsername());
			notification.setProjectId(project.getId());
			notification.setNotificationDate(new Date());

			notification.setStatus(project.getStatus());

			return notification;
		} else {
			Logger.debug("Object " + object.getClass().getName()
					+ " is not an instance of " + Project.class.getSimpleName());
			return null;
		}
	}

}
