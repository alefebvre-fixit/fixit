package controllers;

import play.mvc.Controller;

import com.fixit.model.User;
import com.fixit.service.ProjectService;
import com.fixit.service.UserService;
import com.fixit.service.impl.MongoProjectService;
import com.fixit.service.impl.TestProjectService;
import com.fixit.service.impl.TestUserService;

public class FixItController extends Controller {

	public static final String SESSION_ATTRIBUTE_USERNAME = "username";
	
	private static UserService userService = new TestUserService();
	private static ProjectService projectService = new TestProjectService();

	protected static UserService getUserService() {
		return userService;
	}
	
	protected static ProjectService getProjectService() {
		return projectService;
	}

	protected static User getUser() {
		return userService.load(getUserName());
	}

	protected static String getUserName() {
		return session().get(SESSION_ATTRIBUTE_USERNAME);
	}

}
