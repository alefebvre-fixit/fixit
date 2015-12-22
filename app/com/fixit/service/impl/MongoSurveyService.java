package com.fixit.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import play.Logger;

import com.fixit.dao.SurveyRepository;
import com.fixit.model.survey.Survey;
import com.fixit.service.NotificationService;
import com.fixit.service.SurveyService;

@Named
public class MongoSurveyService implements SurveyService {

	public static final String USER_NAME = "username";
	public static final String SURVEY_ID = "surveyId";
	public static final String EVENT_ID = "eventId";

	@Inject
	SurveyRepository surveyRepository;

	@Inject
	private NotificationService notificationService;

	@Override
	public void delete(String id) {
		Logger.debug("MongoEventService.delete(String id) id=" + id);
		surveyRepository.delete(id);
	}

	@Override
	public Survey save(Survey survey) {
		notificationService.publishNotification(survey);

		Survey result = surveyRepository.save(survey);
		return result;
	}

	@Override
	public Survey getSurvey(String id) {
		return surveyRepository.findOne(id);
	}

	@Override
	public List<Survey> getAll() {
		return surveyRepository.findAll();
	}

}
