package com.fixit.service;

import java.util.List;

import com.fixit.model.User;
import com.fixit.model.group.Group;

public interface GroupService {

	public List<Group> getAll();

	public String create(Group group);

	public Group save(Group group);

	public void delete(String id);

	public Group getGroup(String id);

	public int countGroupsByOwner(String username);

	public List<Group> getUserGroups(String username, int offset, int length);

	public void follow(String username, String groupId);

	public void unfollow(String username, String groupId);

	public List<String> groupFollowed(String username);

	public List<User> groupFollowers(String groupId);

	public List<String> groupFollowerNames(String groupId);

	public int groupFollowersSize(String groupId);

	public String getGroupOwner(String groupId);

}
