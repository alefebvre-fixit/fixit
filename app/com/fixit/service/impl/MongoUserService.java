package com.fixit.service.impl;

import java.util.List;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import play.Logger;

import com.fixit.model.User;
import com.fixit.model.account.SignIn;
import com.fixit.model.account.SignUp;
import com.fixit.model.account.UserCard;
import com.fixit.service.UserService;

public class MongoUserService implements UserService {
	
	private static final String USER_NAME = "username";
	private static final String EMAIL = "email";

	
	@Override
	public User load(String username) {
		Logger.debug("MongoUserService.load(String userName) username=" + username);

		User result = null;
		DBCursor<User> cursor = getCollection().find().is("username", username);
		if (cursor.hasNext()){
			result = cursor.next();
		}
		
		return result;
	}

	@Override
	public User authenticateByEmail(String email, String password) {
		
		User user = null;
				
		DBCursor<User> cursor = getCollection().find().is(EMAIL, email);
		if (cursor.hasNext()){
			user = cursor.next();
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
		WriteResult<User, String> result = null;
		if (user.getId() == null) {
			Logger.debug("MongoUserService.save()");
			result = getCollection().insert(user);
			user.setId(result.getSavedId());
		} else {
			Logger.debug("MongoUserService.save.updateById(String id) username="
					+ user.getUsername());

			result = getCollection().updateById(user.getId(), user);
		}

		return user;
	}
	
	
	@Override
	public List<User> getAll() {
		Logger.debug("MongoUserService.getAll()");
		return getCollection().find().toArray();
	}

	@Override
	public void delete(String id) {
		Logger.debug("MongoUserService.delete(String id) id=" + id);
		getCollection().removeById(id);
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
	
	
	private JacksonDBCollection<User, String> getCollection() {
		return MongoDBPersistence.getUserCollection();
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
		
		getCollection().updateById(followerUser.getId(), followerUser);
		getCollection().updateById(followeeUser.getId(), followeeUser);

		
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
		
		getCollection().updateById(followerUser.getId(), followerUser);
		getCollection().updateById(followeeUser.getId(), followeeUser);

		
		return followerUser;
	}

	@Override
	public List<User> getFollowers(String username) {
		
		List<User> result = null;
		
		User user = load(username);
		if (user != null){
			List<String> followers = user.getFollowers();
			result = getCollection().find().in(USER_NAME, followers).toArray();
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
