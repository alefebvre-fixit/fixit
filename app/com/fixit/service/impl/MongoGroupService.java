package com.fixit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import play.Logger;

import com.fixit.model.Favorite;
import com.fixit.model.group.Group;
import com.fixit.model.User;
import com.fixit.service.GroupService;
import com.fixit.service.UserService;

public class MongoGroupService implements
		GroupService {

	public static final String USER_NAME = "username";
	public static final String GROUP_ID = "groupId";
	public static final String CARD_ID = "cardId";

	private JacksonDBCollection<Group, String> getCollection() {
		return MongoDBPersistence.getGroupCollection();
	}

	private JacksonDBCollection<Favorite, String> getFavoritesCollection() {
		return MongoDBPersistence.getFavoritesCollection();
	}

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public MongoGroupService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public List<Group> getAll() {
		Logger.debug("MongoGroupService.getAll()");
		return getCollection().find().toArray();
	}

	@Override
	public void delete(String id) {
		Logger.debug("MongoGroupService.delete(String id) id=" + id);

		getCollection().removeById(id);
	}

	@Override
	public String create(Group group) {
		Group result = save(group);
		return result.getId();
	}

	@Override
	public Group save(Group group) {
		WriteResult<Group, String> result = null;
		group.incrementVersion();
		if (group.getId() == null) {
			Logger.debug("MongoGroupService.save.insert()");
			result = getCollection().insert(group);
			group.setId(result.getSavedId());
		} else {
			Logger.debug("MongoGroupService.save.updateById(String id) id="
					+ group.id);

			result = getCollection().updateById(group.id, group);
		}

		return group;
	}

	@Override
	public Group getGroup(String id) {
		Logger.debug("MongoGroupService.load(String id) id=" + id);
		Group result = getCollection().findOneById(id);
		return result;
	}

	@Override
	public int countGroupsByOwner(String username) {
		int result = getCollection().find().is(USER_NAME,
				username).count();
		Logger.debug("countGroupsByOwner(String owner) owner=" + username
				+ "result = " + result);
		return result;
	}

	@Override
	public List<Group> getUserGroups(String username, int offset, int length) {
		DBCursor<Group> cursor = getCollection().find().is(USER_NAME,
				username);
		if (offset > 0) {
			cursor.skip(offset);
		}
		if (length > 0) {
			cursor.limit(length);
		}
		return cursor.toArray(MongoDBPersistence.MAX_OBJECT);
	}

	@Override
	public void follow(String username, String groupId) {
		Favorite favorite = new Favorite();
		favorite.setCreationDate(new Date());
		favorite.setUsername(username);
		favorite.setGroupId(groupId);

		int count = getFavoritesCollection().find().is(USER_NAME, username)
				.is(GROUP_ID, groupId).count();
		if (count <= 0) {
			getFavoritesCollection().insert(favorite);
		}
	}

	@Override
	public void unfollow(String username, String groupId) {
		List<Favorite> favorites = getFavoritesCollection().find()
				.is(USER_NAME, username).is(GROUP_ID, groupId).toArray();
		if (favorites != null) {
			for (Favorite favorite : favorites) {
				getFavoritesCollection().removeById(favorite.getId());
			}
		}
	}

	@Override
	public List<String> groupFollowed(String username) {
		List<String> result = new ArrayList<String>();

		List<Favorite> favorites = getFavoritesCollection().find()
				.is(USER_NAME, username).toArray();
		if (favorites != null) {
			for (Favorite favorite : favorites) {
				result.add(favorite.getGroupId());
			}
		}
		return result;
	}

	@Override
	public String getGroupOwner(String groupId) {
		// TODO Improve implementation by loading only the username

		String result = null;

		Group group = getGroup(groupId);
		if (group != null) {
			result = group.getUsername();
		}

		return result;
	}

	@Override
	public int groupFollowersSize(String groupId) {
		return getFavoritesCollection().find().is(GROUP_ID, groupId)
				.count();
	}

	@Override
	public List<String> groupFollowerNames(String groupId) {
		// TODO Improve implementation by loading only the username

		List<String> result = new ArrayList<String>();

		List<Favorite> favorites = getFavoritesCollection().find()
				.is(GROUP_ID, groupId).toArray();
		if (favorites != null) {
			for (Favorite favorite : favorites) {
				if (favorite != null) {
					result.add(favorite.getUsername());
				}
			}
		}

		return result;
	}

	@Override
	public List<User> groupFollowers(String groupId) {
		// TODO Improve implementation by loading only the username

		List<User> result = new ArrayList<User>();

		List<Favorite> favorites = getFavoritesCollection().find()
				.is(GROUP_ID, groupId).toArray();
		if (favorites != null) {
			for (Favorite favorite : favorites) {
				User user = userService.load(favorite.getUsername());
				if (user != null) {
					result.add(user);
				}
			}
		}

		return result;
	}

}
