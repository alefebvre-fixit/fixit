package controllers.api;

import java.util.List;

import javax.inject.Named;

import play.Logger;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import play.mvc.Security;

import com.fixit.model.S3File;
import com.fixit.model.user.Profile;
import com.fixit.model.user.YaUser;

import controllers.Secured;
import controllers.YaController;

@Named
@Security.Authenticated(Secured.class)
public class UserAPIController extends YaController {

	public Result account() {
		Logger.debug("UserAPIController.account");

		YaUser user = getUserService().findOne(getUserName());

		return ok(play.libs.Json.toJson(user));
	}

	public Result accounts() {
		Logger.debug("UserAPIController.accounts()");
		return ok(play.libs.Json.toJson(getUserService().findAll()));
	}

	public Result userDiscovery() {
		Logger.debug("UserAPIController.accounts()");
		return ok(play.libs.Json.toJson(getUserService().findAll()));
	}

	public Result user(String username) {
		Logger.debug("UserAPIController.user(username)");
		return ok(play.libs.Json.toJson(getUserService().findOne(username)));
	}

	public Result updateProfile() {
		Logger.debug("UserAPIController.updateAccount()");

		RequestBody body = request().body();
		Profile profile = Json.fromJson(body.asJson(), Profile.class);

		YaUser user = getUser();
		user.setProfile(profile);
		getUserService().save(user);
		return ok(play.libs.Json.toJson(user));

	}

	public Result follow(String followee) {
		Logger.debug("UserAPIController.follow(followee)" + followee);

		if (!getUserName().equals(followee)) {
			getUserService().follow(getUserName(), followee);
		}

		return ok();
	}

	public Result unFollow(String followee) {
		Logger.debug("UserAPIController.unFollow(followee)" + followee);

		if (!getUserName().equals(followee)) {
			getUserService().unFollow(getUserName(), followee);
		}

		return ok();
	}

	public Result followers(String username) {
		Logger.debug("UserAPIController.followers(username)" + username);

		List<YaUser> result = getUserService().getFollowers(username);

		return ok(play.libs.Json.toJson(result));
	}

	public Result followerSize(String username) {
		Logger.debug("UserAPIController.followerSize username =" + username);
		return ok(Json.toJson(getUserService().countFollowers(username)));
	}

	public Result following(String username) {
		Logger.debug("UserAPIController.following username =" + username);
		return ok(Json.toJson(getUserService().findFollowing(username)));
	}

	public Result followingNames(String username) {
		Logger.debug("UserAPIController.followingNames username =" + username);
		return ok(Json.toJson(getUserService().findFollowingNames(username)));
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
