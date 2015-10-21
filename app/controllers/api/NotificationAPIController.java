package controllers.api;

import java.util.List;

import play.Logger;
import play.mvc.Result;
import play.mvc.Security;

import com.fixit.model.notification.Notification;

import controllers.FixItController;
import controllers.Secured;

public class NotificationAPIController extends FixItController {

	//@Security.Authenticated(Secured.class)
	public Result notifications() {
		Logger.debug("NotificationAPIController.notifications");

		List<Notification> notifications = getNotificationService()
				.getUserNotifications(getUserName(), 0, 0);

		return ok(play.libs.Json.toJson(notifications));
	}

}
