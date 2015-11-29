package com.fixit.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fixit.model.user.User;

public interface UserRepository extends MongoRepository<User, String> {


	List<User> findByEmail(String email);
	
	List<User> findByUsername(String username);
	
	List<User> findByUsernameIn(List<String> usernames);

}