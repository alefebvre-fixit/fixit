package com.fixit.model.notification.impl;

import java.util.Date;

import play.Logger;

import com.fixit.model.notification.Notification;
import com.fixit.model.notification.NotificationFactory;
import com.fixit.model.project.ProjectComment;

public class CommentNotificationFactory extends NotificationFactory {

	@Override
	public Notification createNotification(Object object) {
		if (object instanceof ProjectComment) {
			ProjectComment comment = (ProjectComment) object;
			Notification notification = new Notification();

			notification.setType(Notification.TYPE_COMMENTS);
			notification.setActor(comment.getUsername());
			notification.setProjectId(comment.getId());
			notification.setNotificationDate(new Date());

			return notification;
		} else {
			Logger.debug("Object " + object.getClass().getName()
					+ " is not an instance of "
					+ ProjectComment.class.getSimpleName());
			return null;
		}
	}

}
