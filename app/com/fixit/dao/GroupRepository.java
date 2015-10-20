package com.fixit.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.fixit.model.group.Group;

public interface GroupRepository extends MongoRepository<Group, String> {

	public int countByUsername(String username);

	public Page<Group> findByUserName(String username, Pageable pageable);

}