package controllers;

import javax.inject.Inject;
import javax.inject.Named;

import play.mvc.Controller;

import com.fixit.model.user.YaUser;
import com.fixit.service.EventService;
import com.fixit.service.GroupService;
import com.fixit.service.NotificationService;
import com.fixit.service.SurveyService;
import com.fixit.service.UserService;

@Named
public class YaController extends Controller {

	public static final String SESSION_ATTRIBUTE_USERNAME = "username";
	public static final String SESSION_ATTRIBUTE_ACCESS_TOKEN = "access_token";

	@Inject
	private UserService userService;

	@Inject
	private EventService eventService;

	@Inject
	private GroupService groupService;

	@Inject
	private SurveyService surveyService;
	
	@Inject
	private NotificationService notificationService;
	
	protected NotificationService getNotificationService(){
		return notificationService;
	}
	
	protected UserService getUserService() {
		return userService;
	}

	protected EventService getEventService() {
		return eventService;
	}

	protected GroupService getGroupService() {
		return groupService;
	}

	protected YaUser getUser() {
		return userService.findOne(getUserName());
	}

	protected SurveyService getSurveyService() {
		return surveyService;
	}
	
	protected static String getUserName() {
		return session().get(SESSION_ATTRIBUTE_USERNAME);
	}

	protected static String getAccessToken() {
		return session().get(SESSION_ATTRIBUTE_ACCESS_TOKEN);
	}

}
