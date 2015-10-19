package controllers.api;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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

import controllers.FixItController;
import controllers.Secured;

public class AccountAPIController extends FixItController {

	@Inject
	private GroupService groupService;
	
	protected GroupService getGroupService() {
		return groupService;
	}
	
	@Security.Authenticated(Secured.class)
	public static Result accountSummary() {
		Logger.debug("AccountAPIController.accountSummary");

		User user = getUserService().load(getUserName());

		return ok(play.libs.Json.toJson(user.getUserCard()));
	}

	@Security.Authenticated(Secured.class)
	public static Result account() {
		Logger.debug("AccountAPIController.account");

		User user = getUserService().load(getUserName());

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
		if (messages.size() == 0) {
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
	
	public static Result googleSignIn() {

		Logger.debug("AccountAPIController.googleSignIn()");

		RequestBody body = request().body();
		Logger.debug("AccountAPIController.googleSignIn()" + body.asJson());

		
		//SignIn signin = Json.fromJson(body.asJson(), SignIn.class);
		/*
		User user = getUserService().authenticate(signin);

		if (user == null) {
			return forbidden("Invalid password");
		}
		session().clear();
		session(SESSION_ATTRIBUTE_USERNAME, user.getUsername());
		*/
		
		return ok(body.asJson());

	}
	
	
	

	// TODO use bean validation
	public static List<String> validate(SignUp signup) {
		List<String> result = new ArrayList<>();

		User user = getUserService().load(signup.getUsername());
		if (user != null) {
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

	public Result userSummary(String username) {
		Logger.debug("AccountAPIController.user(username)");

		UserSummary result = new UserSummary();
		result.setUser(getUserService().load(username));

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
		
		result.setFollowerNumber(getUserService().countFollowers(username));
		

		return ok(play.libs.Json.toJson(result));
	}

	public static Result follow(String followee) {
		Logger.debug("AccountAPIController.follow(followee)" + followee);

		User result = getUser();
		if (!result.getUsername().equals(followee)) {
			result = getUserService().follow(result.getUsername(), followee);
		}

		return ok(play.libs.Json.toJson(result));
	}

	public static Result unFollow(String followee) {
		Logger.debug("AccountAPIController.unFollow(followee)" + followee);

		User result = getUser();
		if (!result.getUsername().equals(followee)) {
			result = getUserService().unFollow(result.getUsername(), followee);
		}

		return ok(play.libs.Json.toJson(result));
	}

	public static Result followers(String username) {
		Logger.debug("AccountAPIController.followers(username)" + username);

		List<User> result = getUserService().getFollowers(username);

		return ok(play.libs.Json.toJson(result));
	}
	
	
	
	public static Result uploadPicture() {
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
        }
        else {
            return badRequest("File upload error");
        }
		
	}

}
