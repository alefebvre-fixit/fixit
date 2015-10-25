package com.fixit.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fixit.model.test.SpringUser;

public interface SpringUserRepository extends MongoRepository<SpringUser, String> {

	public SpringUser findByFirstName(String firstName);

	public List<SpringUser> findByLastName(String lastName);

}