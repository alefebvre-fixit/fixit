package com.fixit.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import play.Logger;

import com.fixit.dao.FavoriteRepository;
import com.fixit.dao.GroupRepository;
import com.fixit.model.Favorite;
import com.fixit.model.User;
import com.fixit.model.group.Group;
import com.fixit.service.GroupService;
import com.fixit.service.UserService;

@Named
public class MongoGroupService implements GroupService {

	@Inject
	GroupRepository groupRepository;

	@Inject
	FavoriteRepository favoriteRepository;

	@Inject
	private UserService userService;

	@Override
	public List<Group> getAll() {
		Logger.debug("MongoGroupService.getAll()");
		return groupRepository.findAll();
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
		return groupRepository.save(group);
	}

	@Override
	public Group getGroup(String id) {
		return groupRepository.findOne(id);
	}

	@Override
	public int countGroupsByOwner(String username) {
		return groupRepository.countByUsername(username);
	}

	@Override
	public List<Group> getUserGroups(String username, int offset, int length) {
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
	public List<String> groupFollowed(String username) {
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
		return favoriteRepository.countByGroupId(groupId);
	}

	@Override
	public List<String> groupFollowerNames(String groupId) {
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
	public List<User> groupFollowers(String groupId) {

		// TODO Improve implementation by loading only the username
		List<User> result = new ArrayList<User>();

		List<Favorite> favorites = favoriteRepository.findByGroupId(groupId);

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
