package com.fixit.service.impl;

import java.util.List;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import play.Logger;

import com.fixit.model.User;
import com.fixit.model.account.SignIn;
import com.fixit.model.account.SignUp;
import com.fixit.service.UserService;

public class MongoUserService implements UserService {
	
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
				
		DBCursor<User> cursor = getCollection().find().is("email", email);
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
	
}
