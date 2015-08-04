package controllers.api;

import java.util.List;

import play.Logger;
import play.libs.Json;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

import com.fixit.model.group.Group;
import com.fixit.model.group.GroupFactory;

import controllers.YaController;

//@Security.Authenticated(Secured.class)
public class GroupAPIController extends YaController {

	public static Result groups() {
		Logger.debug("GroupAPIController.groups()");

		return ok(play.libs.Json.toJson(getGroupService().getAll()));
	}

	public static Result createNewGroup() {
		Logger.debug("GroupAPIController.createNewGroup()");

		return ok(Json.toJson(GroupFactory.createGroup(getUser())));
	}

	public static Result publishGroup(String groupId) {
		Group group = getGroupService().getGroup(groupId);
		group.setStatus(Group.STATUS_PUBLISHED);

		group = getGroupService().save(group);

		return ok(Json.toJson(group));
	}

	public static Result createGroup() {
		Logger.debug("GroupAPIController.createGroup()");

		RequestBody body = request().body();

		Group group = Json.fromJson(body.asJson(), Group.class);
		group.username = getUserName();
		group.setStatus(Group.STATUS_DRAFT);
		String id = getGroupService().create(group);
		group.id = id;
		return ok(Json.toJson(group));
	}

	public static Result save() {
		Logger.debug("GroupAPIController.save()");

		RequestBody body = request().body();

		Group group = Json.fromJson(body.asJson(), Group.class);
		group.username = getUserName();

		Group result = getGroupService().save(group);

		return ok(Json.toJson(result));
	}

	public static Result getUserGroups(String username) {
		Logger.debug("GroupAPIController.groupByOwner username =" + username);
		List<Group> groups = getGroupService().getUserGroups(username, -1, -1);
		return ok(Json.toJson(groups));
	}

	public static Result follow(String groupId) {
		Logger.debug("GroupAPIController.follow groupId =" + groupId);

		getGroupService().follow(getUserName(), groupId);

		return ok(Json.toJson(getGroupService().groupFollowed(getUserName())));
	}

	public static Result followerSize(String groupId) {
		Logger.debug("GroupAPIController.followerSize groupId =" + groupId);

		return ok(Json.toJson(getGroupService().groupFollowersSize(groupId)));
	}

	public static Result followers(String groupId) {
		Logger.debug("GroupAPIController.followers groupId =" + groupId);

		return ok(Json.toJson(getGroupService().groupFollowers(groupId)));
	}

	public static Result unfollow(String groupId) {
		Logger.debug("GroupAPIController.unfollow groupId =" + groupId);

		getGroupService().unfollow(getUserName(), groupId);

		return ok(Json.toJson(getGroupService().groupFollowed(getUserName())));
	}

	public static Result favorites(String username) {
		Logger.debug("GroupAPIController.favorites username =" + username);

		return ok(Json.toJson(getGroupService().groupFollowed(username)));
	}

	public static Result group(String groupId) {
		Logger.debug("GroupAPIController.group groupId =" + groupId);
		Group group = getGroupService().getGroup(groupId);
		return ok(Json.toJson(group));
	}

	public static Result deleteGroup(String groupId) {
		Logger.debug("GroupAPIController.deleteGroup groupId =" + groupId);
		getGroupService().delete(groupId);
		return ok();
	}

}
