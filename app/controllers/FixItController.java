package controllers;

import play.mvc.Controller;

import com.fixit.model.User;
import com.fixit.service.CardService;
import com.fixit.service.ContributionService;
import com.fixit.service.GroupService;
import com.fixit.service.NotificationService;
import com.fixit.service.ProjectCommentService;
import com.fixit.service.ProjectService;
import com.fixit.service.UserService;
import com.fixit.service.impl.MongoCardService;
import com.fixit.service.impl.MongoContributionService;
import com.fixit.service.impl.MongoGroupService;
import com.fixit.service.impl.MongoNotificationService;
import com.fixit.service.impl.MongoProjectCommentService;
import com.fixit.service.impl.MongoProjectService;
import com.fixit.service.impl.MongoUserService;

public class FixItController extends Controller {

	public static final String SESSION_ATTRIBUTE_USERNAME = "username";
	public static final String SESSION_ATTRIBUTE_ACCESS_TOKEN = "access_token";


	private static UserService userService = new MongoUserService();
	private static ProjectService projectService = new MongoProjectService(new MongoUserService());
	private static ContributionService contributionService = new MongoContributionService();
	private static CardService cardService = new MongoCardService(
			new MongoContributionService(), new MongoProjectService(new MongoUserService()));
	private static NotificationService notificationService = new MongoNotificationService();
	private static ProjectCommentService commentService = new MongoProjectCommentService();
	private static GroupService groupService = new MongoGroupService(new MongoUserService());
	
	protected static UserService getUserService() {
		return userService;
	}
	
	protected static GroupService getGroupService() {
		return groupService;
	}

	protected static ProjectService getProjectService() {
		return projectService;
	}

	public static ContributionService getContributionService() {
		return contributionService;
	}

	public static CardService getCardService() {
		return cardService;
	}
	
	public static NotificationService getNotificationService() {
		return notificationService;
	}
	
	public static ProjectCommentService getCommentService() {
		return commentService;
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
