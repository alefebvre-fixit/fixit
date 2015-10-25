package controllers;

import javax.inject.Inject;
import javax.inject.Named;

import play.mvc.Controller;

import com.fixit.model.User;
import com.fixit.service.EventService;
import com.fixit.service.GroupService;
import com.fixit.service.UserService;
import com.fixit.service.impl.MongoEventService;
import com.fixit.service.impl.MongoGroupService;
import com.fixit.service.impl.MongoUserService;

@Named
public class YaController extends Controller {

	public static final String SESSION_ATTRIBUTE_USERNAME = "username";
	public static final String SESSION_ATTRIBUTE_ACCESS_TOKEN = "access_token";

	
	@Inject
	private UserService userService;

	@Inject
	private EventService eventService;

	protected UserService getUserService() {
		return userService;
	}

	protected EventService getEventService() {
		return eventService;
	}

	protected User getUser() {
		return userService.load(getUserName());
	}

	protected static String getUserName() {
		return session().get(SESSION_ATTRIBUTE_USERNAME);
	}

	protected static String getAccessToken() {
		return session().get(SESSION_ATTRIBUTE_ACCESS_TOKEN);
	}

}
