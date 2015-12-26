package controllers.api;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import play.Logger;
import play.libs.Json;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import play.mvc.Results;

import com.fixit.model.user.SignUp;
import com.fixit.model.user.YaUser;
import com.fixit.model.user.impl.EmailSignIn;
import com.fixit.model.user.impl.FacebookSignIn;
import com.fixit.model.user.impl.FacebookSignUp;
import com.fixit.util.YaUtil;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.User;

import controllers.YaController;

@Named
public class LoginAPIController extends YaController {

	public Result signUp() {
		Logger.debug("UserAPIController.signup()");

		RequestBody body = request().body();
		SignUp signup = Json.fromJson(body.asJson(), SignUp.class);

		List<String> messages = validate(signup);
		if (messages.size() == 0) {
			session().clear();
			YaUser user = getUserService().signup(signup);
			session(SESSION_ATTRIBUTE_USERNAME, user.getUsername());
			return ok(play.libs.Json.toJson(user));
		} else {
			return Results.badRequest();
		}

	}

	public Result signIn() {

		Logger.debug("UserAPIController.signIn()");

		RequestBody body = request().body();
		EmailSignIn signin = Json.fromJson(body.asJson(), EmailSignIn.class);

		YaUser user = getUserService().authenticate(signin);

		if (user == null) {
			return forbidden("Invalid password");
		}
		session().clear();
		session(SESSION_ATTRIBUTE_USERNAME, user.getUsername());

		return ok(play.libs.Json.toJson(user));

	}

	public Result googleSignIn() {

		Logger.debug("UserAPIController.googleSignIn()");
		RequestBody body = request().body();
		Logger.debug("UserAPIController.googleSignIn()" + body.asJson());

		// SignIn signin = Json.fromJson(body.asJson(), SignIn.class);
		/*
		 * User user = getUserService().authenticate(signin);
		 * 
		 * if (user == null) { return forbidden("Invalid password"); }
		 * session().clear(); session(SESSION_ATTRIBUTE_USERNAME,
		 * user.getUsername());
		 */

		return ok(body.asJson());

	}

	String APP_SECRET = "07682c1ea374c2ca6eaa6fcff5ebb589";

	public Result facebookSignIn() {
		Logger.debug("UserAPIController.facebookSignIn()");

		YaUser user = null;

		RequestBody body = request().body();
		FacebookSignIn signin = Json.fromJson(body.asJson(),
				FacebookSignIn.class);

		Logger.debug("UserAPIController.facebookSignIn(String token) token="
				+ signin.getToken());
		FacebookClient facebookClient = new DefaultFacebookClient(
				signin.getToken(), APP_SECRET, Version.VERSION_2_5);

		User facebook = facebookClient.fetchObject("me", User.class,
				Parameter.with("fields", "id,name,email,bio,website"));

		if (facebook != null) {
			Logger.debug("UserAPIController.facebookSignIn() retrieved user from facebook="
					+ facebook);

			user = getUserService().findOneByEmail(facebook.getEmail());

			// We do not know this user, this is actually a sign-up
			if (user == null) {
				Logger.debug("UserAPIController.facebookSignIn() unknown user from facebook="
						+ facebook);
				SignUp signup = new FacebookSignUp(facebook);
				user = getUserService().signup(signup);
			} else {
				if (YaUtil.isEmpty(user.getFacebookId())) {
					// merge with existing account
					user.setFacebookId(facebook.getId());
					user = getUserService().save(user);
				}
			}
		}

		if (user == null) {
			Logger.debug("UserAPIController.facebookSignIn() cannot identify user=");
			session().clear();
			return forbidden("Invalid password");
		}

		session().clear();
		session(SESSION_ATTRIBUTE_USERNAME, user.getUsername());
		session(SESSION_ATTRIBUTE_ACCESS_TOKEN, signin.getToken());

		return ok(play.libs.Json.toJson(user));
	}

	// TODO use bean validation
	public List<String> validate(SignUp signup) {
		List<String> result = new ArrayList<String>();

		YaUser user = getUserService().findOne(signup.getUsername());
		if (user != null) {
			Logger.debug("UserAPIController.validate() : User already exist");
			result.add("User already exist");
		}

		return result;
	}

}
