package com.fixit.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import play.Logger;

import com.fixit.model.User;
import com.fixit.model.account.SignIn;
import com.fixit.model.account.SignUp;
import com.fixit.service.UserService;

public class TestUserService implements UserService {

	@Override
	public User load(String username) {
		Logger.debug("User.get username=" + username);
		return users.get(username);
	}

	@Override
	public User authenticateByEmail(String email, String password) {
		Logger.debug("authenticateByEmail email=" + email);
		Logger.debug("authenticateByEmail password=" + password);

		for (User user : users.values()) {
			if (user.getEmail().equals(email)) {
				if (user.password.equals(password)) {
					return user;
				} else {
					return null;
				}
			}
		}
		return null;
	}

	@Override
	public User authenticateByUserName(String username, String password) {
		Logger.debug("authenticateByUserName username=" + username);
		Logger.debug("authenticateByUserName password=" + password);

		User user = users.get(username);
		if (user != null) {
			if (user.password.equals(password)) {
				return user;
			} else {
				return null;
			}
		}

		return null;
	}

	public static final Map<String, User> users = User.all();

	@Override
	public String create(User user) {
		users.put(user.getUsername(), user);
		return user.getUsername();
	}

	@Override
	public User save(User user) {
		users.put(user.getUsername(), user);
		return user;
	}

	@Override
	public void delete(String user) {
		users.remove(user);
	}

	@Override
	public List<User> getAll() {
		return new ArrayList<User>(users.values());
	}

	@Override
	public User signup(SignUp signup) {
		User user = new User(signup);
		save(user);
		return user;
	}

	@Override
	public User authenticate(SignIn signin) {
		return authenticateByUserName(signin.getUsername(), signin.getPassword());
	}
	
	@Override
	public User follow(String follower, String followee) {
		User user = load(follower);
		if (!user.getFollowing().contains(followee)){
			user.getFollowing().add(followee);
		}
		return user;
	}

	@Override
	public User unFollow(String follower, String followee) {
		User user = load(follower);
		if (user.getFollowing().contains(followee)){
			user.getFollowing().remove(followee);
		}
		return user;
	}

}
