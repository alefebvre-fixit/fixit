package controllers;

import play.mvc.Controller;

import com.fixit.model.User;
import com.fixit.service.EventService;
import com.fixit.service.GroupService;
import com.fixit.service.UserService;
import com.fixit.service.impl.MongoEventService;
import com.fixit.service.impl.MongoGroupService;
import com.fixit.service.impl.MongoUserService;

public class YaController extends Controller {

	public static final String SESSION_ATTRIBUTE_USERNAME = "username";
	public static final String SESSION_ATTRIBUTE_ACCESS_TOKEN = "access_token";

	private static UserService userService = new MongoUserService();

	private static GroupService groupService = new MongoGroupService(
			new MongoUserService());

	private static EventService eventService = new MongoEventService();

	protected static UserService getUserService() {
		return userService;
	}

	protected static GroupService getGroupService() {
		return groupService;
	}

	protected static EventService getEventService() {
		return eventService;
	}

	protected static User getUser() {
		return userService.load(getUserName());
	}

	protected static String getUserName() {
		return session().get(SESSION_ATTRIBUTE_USERNAME);
	}

	protected static String getAccessToken() {
		return session().get(SESSION_ATTRIBUTE_ACCESS_TOKEN);
	}

}
