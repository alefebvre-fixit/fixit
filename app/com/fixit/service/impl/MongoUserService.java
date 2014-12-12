package com.fixit.service.impl;

import java.util.List;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;

import play.Logger;
import play.modules.mongojack.MongoDB;

import com.fixit.model.Project;
import com.fixit.model.User;
import com.fixit.model.account.SignIn;
import com.fixit.model.account.SignUp;
import com.fixit.service.UserService;

public class MongoUserService implements UserService {

	private static JacksonDBCollection<User, String> coll = MongoDB
			.getCollection("User", User.class, String.class);
	
	@Override
	public User load(String username) {
		Logger.debug("MongoUserService.load(String userName) username=" + username);
		User result = coll.findOneById(username);
		return result;
	}

	@Override
	public User authenticateByEmail(String email, String password) {
		
		User user = null;
				
		DBCursor<User> cursor = coll.find().is("email", email);
		user = cursor.next();
		
		if (authenticate(user, password)){
			return user;
		} else {
			return null;
		}
		
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<User> getAll() {
		Logger.debug("MongoUserService.getAll()");
		return coll.find().toArray();
	}

	@Override
	public void delete(String id) {
		Logger.debug("MongoUserService.delete(String id) id=" + id);
		coll.removeById(id);
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
	
}
