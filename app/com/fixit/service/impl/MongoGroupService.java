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
import com.fixit.model.user.User;
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
	public List<Group> getAll() {
		Logger.debug("MongoGroupService.getAll()");
		return groupRepository.findAll(new Sort(Sort.Direction.DESC, "creationDate"));
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
		if (!group.getSponsors().contains(group.getUsername())){
			group.getSponsors().add(group.getUsername());
		}
		Group result = groupRepository.save(group);
		
		notificationService.publishNotification(group);
		
		return result;
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
	public List<String> getFollowingIds(String username) {
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

		Logger.debug("MongoGroupService.groupFollowers(String groupId) groupId=" + groupId);
		List<User> result = new ArrayList<User>();

		List<Favorite> favorites = favoriteRepository.findByGroupId(groupId);

		if (favorites != null) {
			List<String> usernames = new ArrayList<String>();
			for (Favorite favorite : favorites) {
				usernames.add(favorite.getUsername());
			}
			result = userService.load(usernames);
		}
		
		if (result == null){
			result = new ArrayList<User>();
		}
		

		return result;
	}

	@Override
	public int groupFollowingSize(String username) {
		Logger.debug("MongoGroupService.groupFollowingSize(String username) username=" + username);
		return favoriteRepository.countByUsername(username);
	}

	@Override
	public List<Group> getFollowingGroups(String username) {
		List<Group> result = new ArrayList<Group>();
		
		List<String> ids = getFollowingIds(username);
		if (YaUtil.isNotEmpty(ids)){
			Iterable<Group> groups = groupRepository.findAll(ids);
			for (Group group : groups) {
				result.add(group);
			}
		}
		
		return result;
	}

	@Override
	public List<User> groupSponsors(String groupId) {
		
		Logger.debug("MongoGroupService.groupSponsors(String groupId) groupId=" + groupId);

		List<User> result = new ArrayList<User>();
		Group group = getGroup(groupId);
		if (group != null) {
			result = userService.load(group.getSponsors());
		}
		
		if (result == null){
			result = new ArrayList<User>();
		}
		
		return result;
	}

}
