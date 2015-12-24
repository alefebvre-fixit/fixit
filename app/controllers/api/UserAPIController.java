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
import com.fixit.model.user.YaUser;
import com.fixit.model.user.impl.EmailSignIn;
import com.fixit.model.user.impl.FacebookSignIn;
import com.fixit.model.user.impl.FacebookSignUp;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.User;

import controllers.Secured;
import controllers.YaController;

@Named
public class UserAPIController extends YaController {

	@Security.Authenticated(Secured.class)
	public Result account() {
		Logger.debug("UserAPIController.account");

		YaUser user = getUserService().findOne(getUserName());

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
		return ok(play.libs.Json.toJson(getUserService().findOne(username)));
	}

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
		
		RequestBody body = request().body();
		FacebookSignIn signin = Json.fromJson(body.asJson(), FacebookSignIn.class);
		
		
		Logger.debug("UserAPIController.facebookSignIn(String token) token=" + signin.getToken());
		FacebookClient facebookClient = new DefaultFacebookClient(signin.getToken(), APP_SECRET, Version.VERSION_2_5);
		
		
		User facebook = facebookClient.fetchObject("me", User.class);
		
		if (facebook!= null){
			YaUser user = getUserService().findOneByEmail(facebook.getEmail());
			
			//We do not know this user, this is actually a sign-up
			if (user == null){
				SignUp signup = new FacebookSignUp(facebook);
				user = getUserService().signup(signup);
			}
			
			
			session().clear();
			session(SESSION_ATTRIBUTE_USERNAME, user.getUsername());
			session(SESSION_ATTRIBUTE_ACCESS_TOKEN, signin.getToken());
					
		}
	
		return ok();
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

	@Security.Authenticated(Secured.class)
	public Result updateProfile() {
		Logger.debug("UserAPIController.updateAccount()");

		RequestBody body = request().body();
		Profile profile = Json.fromJson(body.asJson(), Profile.class);

		YaUser user = getUser();
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

		List<YaUser> result = getUserService().getFollowers(username);

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
