package com.fixit.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fixit.model.survey.Survey;

public interface SurveyRepository extends MongoRepository<Survey, String> {

}