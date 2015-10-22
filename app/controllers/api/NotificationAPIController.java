package controllers.api;

import java.util.List;

import javax.inject.Named;

import play.Logger;
import play.mvc.Result;

import com.fixit.model.notification.Notification;

import controllers.FixItController;

@Named
public class NotificationAPIController extends FixItController {

	// @Security.Authenticated(Secured.class)
	public Result notifications() {
		Logger.debug("NotificationAPIController.notifications");

		List<Notification> notifications = getNotificationService()
				.getUserNotifications(getUserName(), 0, 0);

		return ok(play.libs.Json.toJson(notifications));
	}

}
