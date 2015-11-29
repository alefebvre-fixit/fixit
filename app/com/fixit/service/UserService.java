package com.fixit.service;

import java.util.List;

import com.fixit.model.user.SignIn;
import com.fixit.model.user.SignUp;
import com.fixit.model.user.User;

public interface UserService {

	public User load(String username);

	public User authenticate(SignIn signin);

	public User authenticateByEmail(String email, String password);

	public User authenticateByUserName(String username, String password);

	public String create(User user);

	public User save(User user);

	public void delete(String id);

	public List<User> getAll();

	public List<User> getFollowers(String username);
	
	public List<String> getFollowerNames(String username);

	public int countFollowers(String username);

	public User signup(SignUp signup);

	public void follow(String follower, String followee);

	public void unFollow(String follower, String followee);

	public List<User> getFollowing(String username);

	public List<String> getFollowingNames(String username);
	
	public int countFollowing(String username);

}
