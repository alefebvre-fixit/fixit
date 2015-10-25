package com.fixit.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import play.Logger;

import com.fixit.dao.UserRepository;
import com.fixit.model.User;
import com.fixit.model.account.SignIn;
import com.fixit.model.account.SignUp;
import com.fixit.model.account.UserCard;
import com.fixit.service.UserService;

@Named
public class MongoUserService implements UserService {
	
	private static final String USER_NAME = "username";
	private static final String EMAIL = "email";
	
	@Inject
	private UserRepository userRepository;
	
	@Override
	public User load(String username) {
		Logger.debug("MongoUserService.load(String userName) username=" + username);
		User result = null;
		
		List<User> users = userRepository.findByUsername(username);
		if (users != null && users.size() > 0){
			result = users.get(0);
		}
		
		return result;
	}

	@Override
	public User authenticateByEmail(String email, String password) {
		User user = null;
		
		List<User> users = userRepository.findByEmail(email);
		if (users != null && users.size() > 0){
			user = users.get(0);
			if (authenticate(user, password)){
				return user;
			}
		}
		
		return null;
		
	}

	@Override
	public User authenticateByUserName(String username, String password) {
		User user = load(username);
		
		if (authenticate(user, password)){
			return user;
		} else {
			return null;
		}
	}
	
	private boolean authenticate(User user, String password) {
		
		if (user == null){
			return false;
		}

		if (password == null){
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
		return authenticateByUserName(signin.getUsername(), signin.getPassword());
	}
	
	


	@Override
	public User follow(String follower, String followee) {
		
		//TODO Externalize relationship
		
		User followerUser = load(follower);
		User followeeUser = load(followee);
		
		if (!followerUser.getFollowing().contains(followee)){
			followerUser.getFollowing().add(followee);
		}
		
		if (!followeeUser.getFollowers().contains(follower)){
			followeeUser.getFollowers().add(follower);
		}
		
		userRepository.save(followerUser);
		userRepository.save(followeeUser);
		
		return followerUser;
	}

	@Override
	public User unFollow(String follower, String followee) {
		//TODO Externalize relationship
		
		User followerUser = load(follower);
		User followeeUser = load(followee);
		
		if (followerUser.getFollowing().contains(followee)){
			followerUser.getFollowing().remove(followee);
		}
		
		if (followeeUser.getFollowers().contains(follower)){
			followeeUser.getFollowers().remove(follower);
		}
		
		userRepository.save(followerUser);
		userRepository.save(followeeUser);

		
		return followerUser;
	}

	@Override
	public List<User> getFollowers(String username) {
		return null;

		//TODO To be implemented
		
/*		List<User> result = null;
		
		User user = load(username);
		if (user != null){
			List<String> followers = user.getFollowers();
			result = getCollection().find().in(USER_NAME, followers).toArray();
		}
		
		return result;*/
	}
	
	@Override
	public int countFollowers(String username) {
		
		int result = 0;
		
		User user = load(username);
		if (user != null){
			result = user.getFollowers().size();
		}
		
		return result;
	}

	@Override
	public UserCard getUserCard(String userName) {
		
		User user = load(userName);
		if (user != null){
			return user.getUserCard();
		} else {
			return null;
		}
		
	}

	
}
