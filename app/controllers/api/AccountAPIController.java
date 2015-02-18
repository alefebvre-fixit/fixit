package controllers.api;

import java.util.ArrayList;
import java.util.List;

import play.Logger;
import play.libs.Json;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

import com.fixit.model.Profile;
import com.fixit.model.User;
import com.fixit.model.UserSummary;
import com.fixit.model.account.SignIn;
import com.fixit.model.account.SignUp;

import controllers.FixItController;
import controllers.Secured;

public class AccountAPIController extends FixItController {

	@Security.Authenticated(Secured.class)
	public static Result accountSummary() {
		Logger.debug("AccountAPIController.accountSummary");

		User user = getUserService().load(session().get("username"));

		return ok(play.libs.Json.toJson(user.getSummary()));
	}

	@Security.Authenticated(Secured.class)
	public static Result account() {
		Logger.debug("AccountAPIController.account");

		User user = getUserService().load(session().get("username"));

		return ok(play.libs.Json.toJson(user));
	}

	public static Result accounts() {
		Logger.debug("AccountAPIController.accounts()");
		return ok(play.libs.Json.toJson(getUserService().getAll()));
	}
	
	public static Result user(String username) {
		Logger.debug("AccountAPIController.user(username)");
		return ok(play.libs.Json.toJson(getUserService().load(username)));
	}
	
	public static Result signUp() {
		Logger.debug("AccountAPIController.signup()");
		
		RequestBody body = request().body();
		SignUp signup = Json.fromJson(body.asJson(), SignUp.class);
		
		List<String> messages = validate(signup);
		if (messages.size() == 0){
			session().clear();
			User user = getUserService().signup(signup);
			session(SESSION_ATTRIBUTE_USERNAME, user.getUsername());
			return ok(play.libs.Json.toJson(user));
		} else {
			return Results.badRequest();
		}
		
	}
	
	public static Result signIn() {
		
		Logger.debug("AccountAPIController.signIn()");
		
		RequestBody body = request().body();
		SignIn signin = Json.fromJson(body.asJson(), SignIn.class);

		User user = getUserService().authenticate(signin);

		if (user == null) {
			return forbidden("Invalid password");
		}
		session().clear();
		session(SESSION_ATTRIBUTE_USERNAME, user.getUsername());
		
		return ok(play.libs.Json.toJson(user));
		
	}
	
	//TODO use bean validation
	public static List<String> validate(SignUp signup){
		List<String> result = new ArrayList<>();
		
		User user = getUserService().load(signup.getUsername());
		if (user != null){
			Logger.debug("AccountAPIController.validate() : User already exist");
			result.add("User already exist");
		}
		
		return result;
	}
	
	
	public static Result updateProfile() {
		Logger.debug("AccountAPIController.updateAccount()");
		
		RequestBody body = request().body();
		Profile profile = Json.fromJson(body.asJson(), Profile.class);
		
		User user = getUser();
		user.setProfile(profile);
		getUserService().save(user);
		return ok(play.libs.Json.toJson(user));
		
	}
	
	
	public static Result userSummary(String username) {
		Logger.debug("AccountAPIController.user(username)");
		
		UserSummary result = new UserSummary();
		result.setUser(getUserService().load(username));
		
		result.setLastProjects(getProjectService().loadByOwner(username, 0, 5));
		result.setProjectNumber(getProjectService().countProjectsByOwner(username));
		
		result.setLastContribution(getProjectService().loadContributions(username, 0, 5));
		result.setContributionNumber(getProjectService().countContributionsByOwner(username));
		
		return ok(play.libs.Json.toJson(result));
	}

	
	
	
}
