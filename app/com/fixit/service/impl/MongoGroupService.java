package com.fixit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import play.Logger;

import com.fixit.dao.FavoriteRepository;
import com.fixit.dao.GroupRepository;
import com.fixit.model.Favorite;
import com.fixit.model.group.Group;
import com.fixit.model.user.YaUser;
import com.fixit.service.GroupService;
import com.fixit.service.NotificationService;
import com.fixit.service.UserService;
import com.fixit.util.YaUtil;

@Named
public class MongoGroupService implements GroupService {

	@Inject
	GroupRepository groupRepository;

	@Inject
	FavoriteRepository favoriteRepository;

	@Inject
	private UserService userService;

	@Inject
	private NotificationService notificationService;

	@Override
	public List<Group> findAll() {
		Logger.debug("MongoGroupService.getAll()");
		return groupRepository.findAll(new Sort(Sort.Direction.DESC,
				"creationDate"));
	}

	@Override
	public void delete(String id) {
		Logger.debug("MongoGroupService.delete(String id) id=" + id);
		groupRepository.delete(id);
	}

	@Override
	public String create(Group group) {
		Group result = save(group);
		return result.getId();
	}

	@Override
	public Group save(Group group) {
		Logger.debug("MongoGroupService.save(Group group) id=" + group.id);

		Group result = groupRepository.save(group);

		notificationService.publishNotification(group);

		return result;
	}

	@Override
	public Group findOne(String id) {
		return groupRepository.findOne(id);
	}

	@Override
	public int countByOwner(String username) {
		return groupRepository.countByUsername(username);
	}

	@Override
	public List<Group> findUserGroups(String username, int offset, int length) {
		List<Group> result = null;

		if (length > 0) {
			Page<Group> pages = groupRepository.findByUsername(username,
					new PageRequest(offset, length));
			result = pages.getContent();
		} else {
			result = groupRepository.findByUsername(username);
		}

		return result;
	}

	@Override
	public void follow(String username, String groupId) {
		Favorite favorite = new Favorite();
		favorite.setCreationDate(new Date());
		favorite.setUsername(username);
		favorite.setGroupId(groupId);

		int count = favoriteRepository.countByUsernameAndGroupId(username,
				groupId);

		if (count <= 0) {
			favoriteRepository.save(favorite);
		}
	}

	@Override
	public void unfollow(String username, String groupId) {
		List<Favorite> favorites = favoriteRepository.findByUsernameAndGroupId(
				username, groupId);

		if (favorites != null) {
			favoriteRepository.delete(favorites);
		}

	}

	@Override
	public List<String> findFollowingIds(String username) {
		List<String> result = new ArrayList<String>();

		List<Favorite> favorites = favoriteRepository.findByUsername(username);
		if (favorites != null) {
			for (Favorite favorite : favorites) {
				result.add(favorite.getGroupId());
			}
		}

		return result;
	}

	@Override
	public String findGroupOwner(String groupId) {
		// TODO Improve implementation by loading only the username

		String result = null;

		Group group = findOne(groupId);
		if (group != null) {
			result = group.getUsername();
		}

		return result;
	}

	@Override
	public int countFollowers(String groupId) {
		return favoriteRepository.countByGroupId(groupId);
	}

	@Override
	public List<String> findFollowerNames(String groupId) {
		// TODO Improve implementation by loading only the username

		List<String> result = new ArrayList<String>();

		List<Favorite> favorites = favoriteRepository.findByGroupId(groupId);
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
	public List<YaUser> findFollowers(String groupId) {

		Logger.debug("MongoGroupService.groupFollowers(String groupId) groupId="
				+ groupId);
		List<YaUser> result = new ArrayList<YaUser>();

		List<Favorite> favorites = favoriteRepository.findByGroupId(groupId);

		if (favorites != null) {
			List<String> usernames = new ArrayList<String>();
			for (Favorite favorite : favorites) {
				usernames.add(favorite.getUsername());
			}
			result = userService.find(usernames);
		}

		if (result == null) {
			result = new ArrayList<YaUser>();
		}

		return result;
	}

	@Override
	public int countFollowingSize(String username) {
		Logger.debug("MongoGroupService.groupFollowingSize(String username) username="
				+ username);
		return favoriteRepository.countByUsername(username);
	}

	@Override
	public List<Group> findFollowingGroups(String username) {
		List<Group> result = new ArrayList<Group>();

		List<String> ids = findFollowingIds(username);
		if (YaUtil.isNotEmpty(ids)) {
			Iterable<Group> groups = groupRepository.findAll(ids);
			for (Group group : groups) {
				result.add(group);
			}
		}

		return result;
	}

	@Override
	public List<YaUser> findSponsors(String groupId) {

		Logger.debug("MongoGroupService.groupSponsors(String groupId) groupId="
				+ groupId);

		List<YaUser> result = new ArrayList<YaUser>();
		Group group = findOne(groupId);
		if (group != null) {
			result = userService.find(group.getSponsors());
		}

		if (result == null) {
			result = new ArrayList<YaUser>();
		}

		return result;
	}

}
