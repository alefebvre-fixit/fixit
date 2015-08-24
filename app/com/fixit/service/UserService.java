package com.fixit.service;

import java.util.List;

import com.fixit.model.User;
import com.fixit.model.account.SignIn;
import com.fixit.model.account.SignUp;
import com.fixit.model.account.UserCard;

public interface UserService {

	public User load(String userName);
	
	public UserCard getUserCard(String userName);
	
	public User authenticate(SignIn signin);

	public User authenticateByEmail(String email, String password);

	public User authenticateByUserName(String username, String password);

	public String create(User user);

	public User save(User user);

	public void delete(String user);

	public List<User> getAll();
	
	public List<User> getFollowers(String user);
	
	public int countFollowers(String user);
		
	public User signup(SignUp signup);
	
	public User follow(String follower, String followee);
	
	public User unFollow(String follower, String followee);


}
