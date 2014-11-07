package com.fixit.service;

import java.util.List;

import com.fixit.model.User;
import com.fixit.model.account.SignUp;

public interface UserService {

	public User load(String userName);

	public User authenticateByEmail(String email, String password);

	public User authenticateByUserName(String username, String password);

	public String create(User user);

	public User save(User user);

	public void delete(String user);

	public List<User> getAll();
	
	public User signup(SignUp signup);

}
