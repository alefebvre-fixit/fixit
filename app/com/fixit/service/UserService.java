package com.fixit.service;

import java.util.List;

import com.fixit.model.user.Credential;
import com.fixit.model.user.SignIn;
import com.fixit.model.user.SignUp;
import com.fixit.model.user.YaUser;

public interface UserService {

	public YaUser findOne(String username);

	public YaUser findOneByEmail(String email);

	public List<YaUser> find(List<String> usernames);

	public YaUser authenticate(SignIn signin);

	public YaUser authenticateByEmail(String email, String password);

	public YaUser authenticateByUserName(String username, String password);

	public String create(YaUser user);

	public YaUser save(YaUser user);

	public void delete(String id);

	public List<YaUser> findAll();

	public List<YaUser> getFollowers(String username);

	public List<String> findFollowerNames(String username);

	public int countFollowers(String username);

	public YaUser signup(SignUp signup);

	public void follow(String follower, String followee);

	public void unFollow(String follower, String followee);

	public List<YaUser> findFollowing(String username);

	public List<String> findFollowingNames(String username);

	public int countFollowing(String username);

	public Credential findCredential(String username);
	
}
