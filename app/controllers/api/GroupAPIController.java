package controllers.api;

import java.util.List;

import javax.inject.Named;

import play.Logger;
import play.libs.Json;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import play.mvc.Security;

import com.fixit.model.group.Group;
import com.fixit.model.group.GroupFactory;

import controllers.Secured;
import controllers.YaController;

//@Security.Authenticated(Secured.class)
@Named
public class GroupAPIController extends YaController {

	@Security.Authenticated(Secured.class)
	public Result groups() {
		Logger.debug("GroupAPIController.groups()");

		return ok(play.libs.Json.toJson(getGroupService().findAll()));
	}

	@Security.Authenticated(Secured.class)
	public Result createNewGroup() {
		Logger.debug("GroupAPIController.createNewGroup()");

		return ok(Json.toJson(GroupFactory.createGroup(getUser())));
	}

	@Security.Authenticated(Secured.class)
	public Result publishGroup(String groupId) {
		Group group = getGroupService().findOne(groupId);

		if (group != null && !group.canUpdate(getUserName())) {
			return forbidden();
		}

		group.setStatus(Group.STATUS_PUBLISHED);

		group = getGroupService().save(group);

		return ok(Json.toJson(group));
	}

	@Security.Authenticated(Secured.class)
	public Result create() {
		Logger.debug("GroupAPIController.create()");

		RequestBody body = request().body();

		Group group = Json.fromJson(body.asJson(), Group.class);
		group.username = getUserName();

		Group result = getGroupService().save(group);

		return ok(Json.toJson(result));
	}

	@Security.Authenticated(Secured.class)
	public Result update(String groupId) {
		Logger.debug("GroupAPIController.update()");

		Group original = getGroupService().findOne(groupId);
		if (original != null && !original.canUpdate(getUserName())) {
			return forbidden();
		}

		RequestBody body = request().body();
		Group group = Json.fromJson(body.asJson(), Group.class);
		group.setId(groupId);
		group.username = getUserName();

		Group result = getGroupService().save(group);

		return ok(Json.toJson(result));
	}

	@Security.Authenticated(Secured.class)
	public Result getUserGroups(String username) {
		Logger.debug("GroupAPIController.groupByOwner username =" + username);
		List<Group> groups = getGroupService().findUserGroups(username, -1, -1);
		return ok(Json.toJson(groups));
	}

	@Security.Authenticated(Secured.class)
	public Result follow(String groupId) {
		Logger.debug("GroupAPIController.follow groupId =" + groupId);

		getGroupService().follow(getUserName(), groupId);

		return ok(Json
				.toJson(getGroupService().findFollowingIds(getUserName())));
	}

	@Security.Authenticated(Secured.class)
	public Result followerSize(String groupId) {
		Logger.debug("GroupAPIController.followerSize groupId =" + groupId);

		return ok(Json.toJson(getGroupService().countFollowers(groupId)));
	}

	@Security.Authenticated(Secured.class)
	public Result followers(String groupId) {
		Logger.debug("GroupAPIController.followers groupId =" + groupId);

		return ok(Json.toJson(getGroupService().findFollowers(groupId)));
	}

	@Security.Authenticated(Secured.class)
	public Result sponsors(String groupId) {
		Logger.debug("GroupAPIController.sponsors groupId =" + groupId);
		return ok(Json.toJson(getGroupService().findSponsors(groupId)));
	}

	@Security.Authenticated(Secured.class)
	public Result unfollow(String groupId) {
		Logger.debug("GroupAPIController.unfollow groupId =" + groupId);

		getGroupService().unfollow(getUserName(), groupId);

		return ok(Json
				.toJson(getGroupService().findFollowingIds(getUserName())));
	}

	@Security.Authenticated(Secured.class)
	public Result followingIds(String username) {
		Logger.debug("GroupAPIController.followingIds username =" + username);
		return ok(Json.toJson(getGroupService().findFollowingIds(username)));
	}

	@Security.Authenticated(Secured.class)
	public Result following(String username) {
		Logger.debug("GroupAPIController.following username =" + username);
		return ok(Json.toJson(getGroupService().findFollowingGroups(username)));
	}

	@Security.Authenticated(Secured.class)
	public Result followingSize(String username) {
		Logger.debug("GroupAPIController.followingSize username =" + username);
		return ok(Json.toJson(getGroupService().countFollowingSize(username)));
	}

	@Security.Authenticated(Secured.class)
	public Result group(String groupId) {
		Logger.debug("GroupAPIController.group groupId =" + groupId);
		Group group = getGroupService().findOne(groupId);
		return ok(Json.toJson(group));
	}

	@Security.Authenticated(Secured.class)
	public Result deleteGroup(String groupId) {

		Group original = getGroupService().findOne(groupId);
		if (original != null && !original.canUpdate(getUserName())) {
			return forbidden();
		}

		Logger.debug("GroupAPIController.deleteGroup groupId =" + groupId);
		getGroupService().delete(groupId);
		return ok();
	}

}
