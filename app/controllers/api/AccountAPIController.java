package controllers.api;

import java.util.ArrayList;
import java.util.List;

import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;
import play.mvc.Http.RequestBody;

import com.fixit.model.Project;
import com.fixit.model.User;
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

	@Security.Authenticated(Secured.class)
	public static Result accounts() {
		Logger.debug("AccountAPIController.accounts()");
		return ok(play.libs.Json.toJson(getUserService().getAll()));
	}
	
	public static Result signup() {
		Logger.debug("AccountAPIController.signup()");
		
		RequestBody body = request().body();
		SignUp signup = Json.fromJson(body.asJson(), SignUp.class);
		
		List<String> messages = validate(signup);
		if (messages.size() == 0){
			return ok(play.libs.Json.toJson(getUserService().signup(signup)));
		} else {
			return Results.badRequest();
		}
		
	}
	
	
	//TODO use bean validation
	public static List<String> validate(SignUp signup){
		List<String> result = new ArrayList<>();
		
		User user = getUserService().load(signup.getUsername());
		if (user != null){
			result.add("User already exist");
		}
		
		return result;
	}
	
	

}
