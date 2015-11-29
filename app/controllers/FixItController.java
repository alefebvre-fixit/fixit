package controllers;

import javax.inject.Inject;
import javax.inject.Named;

import play.mvc.Controller;

import com.fixit.model.user.User;
import com.fixit.service.CardService;
import com.fixit.service.ContributionService;
import com.fixit.service.NotificationService;
import com.fixit.service.ProjectCommentService;
import com.fixit.service.ProjectService;
import com.fixit.service.UserService;
import com.fixit.service.impl.MongoCardService;
import com.fixit.service.impl.MongoContributionService;
import com.fixit.service.impl.MongoProjectCommentService;
import com.fixit.service.impl.MongoProjectService;
import com.fixit.service.impl.MongoUserService;

@Named
public class FixItController extends Controller {

	public static final String SESSION_ATTRIBUTE_USERNAME = "username";
	public static final String SESSION_ATTRIBUTE_ACCESS_TOKEN = "access_token";

	private static ProjectService projectService = new MongoProjectService(
			new MongoUserService());

	private static ContributionService contributionService = new MongoContributionService();
	private static CardService cardService = new MongoCardService(
			new MongoContributionService(), new MongoProjectService(
					new MongoUserService()));
	private static ProjectCommentService commentService = new MongoProjectCommentService();

	protected static ProjectService getProjectService() {
		return projectService;
	}

	public static ContributionService getContributionService() {
		return contributionService;
	}

	public static CardService getCardService() {
		return cardService;
	}

	@Inject
	private NotificationService notificationService;

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public static ProjectCommentService getCommentService() {
		return commentService;
	}

	@Inject
	private UserService userService;

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
