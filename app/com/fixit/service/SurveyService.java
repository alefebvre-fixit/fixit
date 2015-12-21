package com.fixit.service;

import com.fixit.model.survey.Survey;

public interface SurveyService {

	public Survey save(Survey survey);

	public void delete(String id);

	public Survey getSurvey(String id);

}
