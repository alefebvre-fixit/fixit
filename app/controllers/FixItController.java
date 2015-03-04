package controllers;

import play.mvc.Controller;

import com.fixit.model.User;
import com.fixit.service.CardService;
import com.fixit.service.ContributionService;
import com.fixit.service.ProjectService;
import com.fixit.service.UserService;
import com.fixit.service.impl.MongoCardService;
import com.fixit.service.impl.MongoContributionService;
import com.fixit.service.impl.MongoProjectService;
import com.fixit.service.impl.MongoUserService;

public class FixItController extends Controller {

	public static final String SESSION_ATTRIBUTE_USERNAME = "username";

	private static UserService userService = new MongoUserService();
	private static ProjectService projectService = new MongoProjectService();
	private static ContributionService contributionService = new MongoContributionService();
	private static CardService cardService = new MongoCardService(
			new MongoContributionService(), new MongoProjectService());

	protected static UserService getUserService() {
		return userService;
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

	protected static User getUser() {
		return userService.load(getUserName());
	}

	protected static String getUserName() {
		return session().get(SESSION_ATTRIBUTE_USERNAME);
	}

}
