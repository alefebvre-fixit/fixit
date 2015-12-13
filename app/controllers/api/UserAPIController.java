package controllers.api;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import play.Logger;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import play.mvc.Results;

import com.fixit.model.S3File;
import com.fixit.model.user.Profile;
import com.fixit.model.user.SignIn;
import com.fixit.model.user.SignUp;
import com.fixit.model.user.User;
import com.fixit.service.GroupService;
import com.fixit.service.UserService;

import controllers.FixItController;

@Named
public class UserAPIController extends FixItController {

	@Inject
	private GroupService groupService;

	protected GroupService getGroupService() {
		return groupService;
	}

	@Inject
	private UserService userService;

	protected UserService getUserService() {
		return userService;
	}

	public Result account() {
		Logger.debug("UserAPIController.account");

		User user = userService.load(getUserName());

		return ok(play.libs.Json.toJson(user));
	}

	public Result accounts() {
		Logger.debug("UserAPIController.accounts()");
		return ok(play.libs.Json.toJson(userService.getAll()));
	}
	
	public Result userDiscovery() {
		Logger.debug("UserAPIController.accounts()");
		return ok(play.libs.Json.toJson(userService.getAll()));
	}

	public Result user(String username) {
		Logger.debug("UserAPIController.user(username)");
		return ok(play.libs.Json.toJson(userService.load(username)));
	}

	public Result signUp() {
		Logger.debug("UserAPIController.signup()");

		RequestBody body = request().body();
		SignUp signup = Json.fromJson(body.asJson(), SignUp.class);

		List<String> messages = validate(signup);
		if (messages.size() == 0) {
			session().clear();
			User user = userService.signup(signup);
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

		User user = userService.authenticate(signin);

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
		List<String> result = new ArrayList<>();

		User user = userService.load(signup.getUsername());
		if (user != null) {
			Logger.debug("UserAPIController.validate() : User already exist");
			result.add("User already exist");
		}

		return result;
	}

	public Result updateProfile() {
		Logger.debug("UserAPIController.updateAccount()");

		RequestBody body = request().body();
		Profile profile = Json.fromJson(body.asJson(), Profile.class);

		User user = getUser();
		user.setProfile(profile);
		userService.save(user);
		return ok(play.libs.Json.toJson(user));

	}

	public Result follow(String followee) {
		Logger.debug("UserAPIController.follow(followee)" + followee);

		if (!getUserName().equals(followee)) {
			userService.follow(getUserName(), followee);
		}

		return ok();
	}

	public Result unFollow(String followee) {
		Logger.debug("UserAPIController.unFollow(followee)" + followee);

		if (!getUserName().equals(followee)) {
			userService.unFollow(getUserName(), followee);
		}

		return ok();
	}

	public Result followers(String username) {
		Logger.debug("UserAPIController.followers(username)" + username);

		List<User> result = userService.getFollowers(username);

		return ok(play.libs.Json.toJson(result));
	}

	public Result followerSize(String username) {
		Logger.debug("UserAPIController.followerSize username =" + username);
		return ok(Json.toJson(getUserService().countFollowers(username)));
	}

	public Result following(String username) {
		Logger.debug("UserAPIController.following username =" + username);
		return ok(Json.toJson(getUserService().getFollowing(username)));
	}
	
	public Result followingNames(String username) {
		Logger.debug("UserAPIController.followingNames username =" + username);
		return ok(Json.toJson(getUserService().getFollowingNames(username)));
	}

	public Result followingSize(String username) {
		Logger.debug("UserAPIController.followingSize username =" + username);
		return ok(Json.toJson(getUserService().countFollowing(username)));
	}

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
