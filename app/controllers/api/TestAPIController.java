package controllers.api;

import java.util.Arrays;

import javax.inject.Named;

import com.fixit.model.group.Group;

import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Security;
import controllers.Secured;
import controllers.YaController;

@Named
@Security.Authenticated(Secured.class)
public class TestAPIController extends YaController {

	public static Result followingSize(String username) {
		Logger.debug("UserAPIController.followingSize username =" + username);
		return ok(Json.toJson(11));
	}

	public static Result followerSize(String username) {
		Logger.debug("UserAPIController.followerSize username =" + username);
		return ok(Json.toJson(22));
	}
	
	public static Result followingGroupSize(String username) {
		Logger.debug("UserAPIController.followingGroupSize username =" + username);
		return ok(Json.toJson(33));
	}
	
	public static Result following(String username) {
		
		Group group = new Group();
		group.setName("Test");
		group.setDescription("Desc");
		
		return ok(Json.toJson(Arrays.asList(group)));
	}
	
	
	
}
