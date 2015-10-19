package com.fixit.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fixit.model.Project;

public interface ProjectRepository extends MongoRepository<Project, String> {

}