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
import play.mvc.Security;

import com.fixit.model.Profile;
import com.fixit.model.S3File;
import com.fixit.model.User;
import com.fixit.model.account.SignIn;
import com.fixit.model.account.SignUp;
import com.fixit.model.account.UserSummary;
import com.fixit.service.GroupService;
import com.fixit.service.UserService;

import controllers.FixItController;
import controllers.Secured;

@Named
public class AccountAPIController extends FixItController {

	@Inject
	private GroupService groupService;

	protected GroupService getGroupService() {
		return groupService;
	}

	@Inject
	private UserService userService;

	public Result accountSummary() {
		Logger.debug("AccountAPIController.accountSummary");

		User user = userService.load(getUserName());

		return ok(play.libs.Json.toJson(user.getUserCard()));
	}

	public Result account() {
		Logger.debug("AccountAPIController.account");

		User user = userService.load(getUserName());

		return ok(play.libs.Json.toJson(user));
	}

	public Result accounts() {
		Logger.debug("AccountAPIController.accounts()");
		return ok(play.libs.Json.toJson(userService.getAll()));
	}

	public Result user(String username) {
		Logger.debug("AccountAPIController.user(username)");
		return ok(play.libs.Json.toJson(userService.load(username)));
	}

	public Result signUp() {
		Logger.debug("AccountAPIController.signup()");

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

		Logger.debug("AccountAPIController.signIn()");

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

		Logger.debug("AccountAPIController.googleSignIn()");

		RequestBody body = request().body();
		Logger.debug("AccountAPIController.googleSignIn()" + body.asJson());

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
			Logger.debug("AccountAPIController.validate() : User already exist");
			result.add("User already exist");
		}

		return result;
	}

	public Result updateProfile() {
		Logger.debug("AccountAPIController.updateAccount()");

		RequestBody body = request().body();
		Profile profile = Json.fromJson(body.asJson(), Profile.class);

		User user = getUser();
		user.setProfile(profile);
		userService.save(user);
		return ok(play.libs.Json.toJson(user));

	}

	public Result userSummary(String username) {
		Logger.debug("AccountAPIController.user(username)");

		UserSummary result = new UserSummary();
		result.setUser(userService.load(username));

		result.setLastProjects(getProjectService().getUserProjects(username, 0,
				5));
		result.setProjectNumber(getProjectService().countProjectsByOwner(
				username));

		result.setLastGroups(getGroupService().getUserGroups(username, 0, 5));
		result.setGroupNumber(getGroupService().countGroupsByOwner(username));

		result.setLastContribution(getContributionService()
				.getUserContributions(username, 0, 5));
		result.setContributionNumber(getContributionService()
				.countContributionsByOwner(username));

		result.setFollowerNumber(userService.countFollowers(username));

		return ok(play.libs.Json.toJson(result));
	}

	public Result follow(String followee) {
		Logger.debug("AccountAPIController.follow(followee)" + followee);

		User result = getUser();
		if (!result.getUsername().equals(followee)) {
			result = userService.follow(result.getUsername(), followee);
		}

		return ok(play.libs.Json.toJson(result));
	}

	public Result unFollow(String followee) {
		Logger.debug("AccountAPIController.unFollow(followee)" + followee);

		User result = getUser();
		if (!result.getUsername().equals(followee)) {
			result = userService.unFollow(result.getUsername(), followee);
		}

		return ok(play.libs.Json.toJson(result));
	}

	public Result followers(String username) {
		Logger.debug("AccountAPIController.followers(username)" + username);

		List<User> result = userService.getFollowers(username);

		return ok(play.libs.Json.toJson(result));
	}

	public Result uploadPicture() {
		Logger.debug("AccountAPIController.uploadPicture()");

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
