package controllers.api;

import java.util.List;

import javax.inject.Named;

import play.Logger;
import play.mvc.Result;
import play.mvc.Security;

import com.fixit.model.notification.Notification;

import controllers.Secured;
import controllers.YaController;

@Named
public class NotificationAPIController extends YaController {

	@Security.Authenticated(Secured.class)
	public Result notifications() {
		Logger.debug("NotificationAPIController.notifications");

		List<Notification> notifications = getNotificationService()
				.findUserNotifications(getUserName(), 0, 0);

		return ok(play.libs.Json.toJson(notifications));
	}

	@Security.Authenticated(Secured.class)
	public Result acknowledgeGroupNotifications(String groupId) {
		Logger.debug("NotificationAPIController.acknowledgeGroupNotifications groupId"
				+ groupId);

		getNotificationService().acknowledgeGroupNotifications(groupId,
				getUserName());

		return ok();
	}

	@Security.Authenticated(Secured.class)
	public Result acknowledgeEventNotifications(String eventId) {
		Logger.debug("NotificationAPIController.acknowledgeEventNotifications eventId"
				+ eventId);

		getNotificationService().acknowledgeEventNotifications(eventId,
				getUserName());

		return ok();
	}

	@Security.Authenticated(Secured.class)
	public Result acknowledgeNotification(String notificationId) {
		Logger.debug("NotificationAPIController.acknowledgeNotification notificationId"
				+ notificationId);

		getNotificationService().acknowledgeNotification(notificationId);

		return ok();
	}

	@Security.Authenticated(Secured.class)
	public Result acknowledgeNotifications() {
		Logger.debug("NotificationAPIController.acknowledgeNotifications");

		getNotificationService().acknowledgeNotifications(getUserName());

		return ok();
	}

}
