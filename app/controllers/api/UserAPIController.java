package controllers.api;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import play.Logger;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

import com.fixit.model.S3File;
import com.fixit.model.user.Profile;
import com.fixit.model.user.SignIn;
import com.fixit.model.user.SignUp;
import com.fixit.model.user.User;

import controllers.Secured;
import controllers.YaController;

@Named
public class UserAPIController extends YaController {

	@Security.Authenticated(Secured.class)
	public Result account() {
		Logger.debug("UserAPIController.account");

		User user = getUserService().load(getUserName());

		return ok(play.libs.Json.toJson(user));
	}

	@Security.Authenticated(Secured.class)
	public Result accounts() {
		Logger.debug("UserAPIController.accounts()");
		return ok(play.libs.Json.toJson(getUserService().findAll()));
	}

	@Security.Authenticated(Secured.class)
	public Result userDiscovery() {
		Logger.debug("UserAPIController.accounts()");
		return ok(play.libs.Json.toJson(getUserService().findAll()));
	}

	@Security.Authenticated(Secured.class)
	public Result user(String username) {
		Logger.debug("UserAPIController.user(username)");
		return ok(play.libs.Json.toJson(getUserService().load(username)));
	}

	public Result signUp() {
		Logger.debug("UserAPIController.signup()");

		RequestBody body = request().body();
		SignUp signup = Json.fromJson(body.asJson(), SignUp.class);

		List<String> messages = validate(signup);
		if (messages.size() == 0) {
			session().clear();
			User user = getUserService().signup(signup);
			session(SESSION_ATTRIBUTE_USERNAME, user.getUsername());
			return ok(play.libs.Json.toJson(user));
		} else {
			return Results.badRequest();
		}

	}

	public Result signIn() {

		Logger.debug("UserAPIController.signIn()");

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

	// TODO use bean validation
	public List<String> validate(SignUp signup) {
		List<String> result = new ArrayList<String>();

		User user = getUserService().load(signup.getUsername());
		if (user != null) {
			Logger.debug("UserAPIController.validate() : User already exist");
			result.add("User already exist");
		}

		return result;
	}

	@Security.Authenticated(Secured.class)
	public Result updateProfile() {
		Logger.debug("UserAPIController.updateAccount()");

		RequestBody body = request().body();
		Profile profile = Json.fromJson(body.asJson(), Profile.class);

		User user = getUser();
		user.setProfile(profile);
		getUserService().save(user);
		return ok(play.libs.Json.toJson(user));

	}

	@Security.Authenticated(Secured.class)
	public Result follow(String followee) {
		Logger.debug("UserAPIController.follow(followee)" + followee);

		if (!getUserName().equals(followee)) {
			getUserService().follow(getUserName(), followee);
		}

		return ok();
	}

	@Security.Authenticated(Secured.class)
	public Result unFollow(String followee) {
		Logger.debug("UserAPIController.unFollow(followee)" + followee);

		if (!getUserName().equals(followee)) {
			getUserService().unFollow(getUserName(), followee);
		}

		return ok();
	}

	@Security.Authenticated(Secured.class)
	public Result followers(String username) {
		Logger.debug("UserAPIController.followers(username)" + username);

		List<User> result = getUserService().getFollowers(username);

		return ok(play.libs.Json.toJson(result));
	}

	@Security.Authenticated(Secured.class)
	public Result followerSize(String username) {
		Logger.debug("UserAPIController.followerSize username =" + username);
		return ok(Json.toJson(getUserService().countFollowers(username)));
	}

	@Security.Authenticated(Secured.class)
	public Result following(String username) {
		Logger.debug("UserAPIController.following username =" + username);
		return ok(Json.toJson(getUserService().findFollowing(username)));
	}

	@Security.Authenticated(Secured.class)
	public Result followingNames(String username) {
		Logger.debug("UserAPIController.followingNames username =" + username);
		return ok(Json.toJson(getUserService().findFollowingNames(username)));
	}

	@Security.Authenticated(Secured.class)
	public Result followingSize(String username) {
		Logger.debug("UserAPIController.followingSize username =" + username);
		return ok(Json.toJson(getUserService().countFollowing(username)));
	}

	@Security.Authenticated(Secured.class)
	public Result uploadPicture() {
		Logger.debug("UserAPIController.uploadPicture()");

		Http.MultipartFormData body = request().body().asMultipartFormData();
		List<Http.MultipartFormData.FilePart> parts = body.getFiles();
		for (Http.MultipartFormData.FilePart filePart : parts) {
			Logger.debug("filePart.getKey()" + filePart.getKey());
		}
		Http.MultipartFormData.FilePart uploadFilePart = body.getFile("file");
		if (uploadFilePart != null) {
			S3File s3File = new S3File();
			s3File.name = uploadFilePart.getFilename();
			s3File.file = uploadFilePart.getFile();
			s3File.save();
			return ok("File uploaded to S3");
		} else {
			return badRequest("File upload error");
		}

	}

}
