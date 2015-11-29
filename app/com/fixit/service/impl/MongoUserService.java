package com.fixit.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import play.Logger;

import com.fixit.dao.FollowingRepository;
import com.fixit.dao.UserRepository;
import com.fixit.model.user.Following;
import com.fixit.model.user.SignIn;
import com.fixit.model.user.SignUp;
import com.fixit.model.user.User;
import com.fixit.service.UserService;
import com.fixit.util.YaUtil;

@Named
public class MongoUserService implements UserService {

	@Inject
	private UserRepository userRepository;

	@Inject
	private FollowingRepository followingRepository;

	@Override
	public User load(String username) {
		Logger.debug("MongoUserService.load(String userName) username="
				+ username);
		User result = null;

		List<User> users = userRepository.findByUsername(username);
		if (users != null && users.size() > 0) {
			result = users.get(0);
		}

		return result;
	}

	@Override
	public User authenticateByEmail(String email, String password) {
		User user = null;

		List<User> users = userRepository.findByEmail(email);
		if (users != null && users.size() > 0) {
			user = users.get(0);
			if (authenticate(user, password)) {
				return user;
			}
		}

		return null;

	}

	@Override
	public User authenticateByUserName(String username, String password) {
		User user = load(username);

		if (authenticate(user, password)) {
			return user;
		} else {
			return null;
		}
	}

	private boolean authenticate(User user, String password) {

		if (user == null) {
			return false;
		}

		if (password == null) {
			return false;
		}

		return password.equals(user.password);
	}

	@Override
	public String create(User user) {
		User result = save(user);
		return result.getUsername();
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public void delete(String id) {
		userRepository.delete(id);
		Logger.debug("MongoUserService.delete(String id) id=" + id);
	}

	@Override
	public User signup(SignUp signup) {
		User user = new User(signup);
		save(user);
		return user;
	}

	@Override
	public User authenticate(SignIn signin) {
		return authenticateByUserName(signin.getUsername(),
				signin.getPassword());
	}

	@Override
	public void follow(String follower, String followee) {
		Logger.debug("MongoUserService.follow(String follower, String followee) follower=" + follower + " follower= " + follower);

		List<Following> following = followingRepository.findByFolloweeAndFollower(followee, follower);
		if (YaUtil.isEmpty(following)){
			followingRepository.save(Following.create(followee, follower));
		} 
	}

	@Override
	public void unFollow(String follower, String followee) {
		Logger.debug("MongoUserService.unFollow(String follower, String followee) follower=" + follower + " follower= " + follower);
		List<Following> following = followingRepository.findByFolloweeAndFollower(followee, follower);
		if (YaUtil.isNotEmpty(following)){
			followingRepository.delete(following);
		}

	}

	@Override
	public List<User> getFollowers(String username) {
		
		Logger.debug("MongoUserService.getFollowers(String username) username="
				+ username);
		
		List<User> result = null;

		List<String> names = getFollowerNames(username);

		if (YaUtil.isNotEmpty(names)) {
			result = userRepository.findByUsernameIn(names);
		}
		
		if (result == null) {
			result = new ArrayList<User>();
		}
		
		Logger.debug("MongoUserService.getFollowers(String username) result=" + result.size());

		return result;
	}

	@Override
	public int countFollowers(String username) {
		Logger.debug("MongoUserService.countFollowers(String username) username="
				+ username);
		return followingRepository.countByFollowee(username);
	}

	@Override
	public List<User> getFollowing(String username) {
		List<User> result = null;
		
		Logger.debug("MongoUserService.getFollowing(String username) username="
				+ username);

		List<String> names = getFollowingNames(username);
		
		if (YaUtil.isNotEmpty(names)) {
			result = userRepository.findByUsernameIn(names);
		}

		if (result == null) {
			result = new ArrayList<User>();
		}
		
		return result;
	}

	@Override
	public int countFollowing(String username) {
		Logger.debug("MongoUserService.countFollowing(String username) username="
				+ username);
		return followingRepository.countByFollower(username);
	}

	@Override
	public List<String> getFollowerNames(String username) {
		Logger.debug("MongoUserService.getFollowerNames(String username) username="
				+ username);

		List<String> result = new ArrayList<String>();

		List<Following> following = followingRepository
				.findByFollowee(username);
		if (YaUtil.isNotEmpty(following)) {
			for (Following f : following) {
				Logger.debug(f.toString());
				result.add(f.getFollower());
			}
		}

		return result;
	}

	@Override
	public List<String> getFollowingNames(String username) {
		Logger.debug("MongoUserService.getFollowingNames(String username) username="
				+ username);

		List<String> result = new ArrayList<String>();

		List<Following> following = followingRepository
				.findByFollower(username);
		if (following != null && following.size() > 0) {
			for (Following f : following) {
				Logger.debug(f.toString());
				result.add(f.getFollowee());
			}
		}

		return result;
	}

}
