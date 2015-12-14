package com.fixit.service;

import java.util.List;

import com.fixit.model.group.Group;
import com.fixit.model.user.User;

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

	public List<String> getFollowingIds(String username);

	public List<Group> getFollowingGroups(String username);
	
	public int groupFollowingSize(String username);
	
	public List<User> groupFollowers(String groupId);
	
	public List<User> groupSponsors(String groupId);

	public List<String> groupFollowerNames(String groupId);

	public int groupFollowersSize(String groupId);

	public String getGroupOwner(String groupId);

	
}
