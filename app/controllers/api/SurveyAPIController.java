package controllers.api;

import javax.inject.Named;

import play.Logger;
import play.libs.Json;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

import com.fixit.model.survey.Survey;

import controllers.YaController;

//@Security.Authenticated(Secured.class)
@Named
public class SurveyAPIController extends YaController {

	public Result update(String surveyId) {
		Logger.debug("SurveyAPIController.update()");

		Survey original = getSurveyService().getSurvey(surveyId);

		if (original != null && !original.canUpdate(getUserName())) {
			return forbidden();
		}

		RequestBody body = request().body();

		Survey survey = Json.fromJson(body.asJson(), Survey.class);
		survey.setUsername(getUserName());
		survey.setId(surveyId);
		Survey result = getSurveyService().save(survey);

		return ok(Json.toJson(result));
	}

	public Result create() {
		Logger.debug("SurveyAPIController.save()");

		RequestBody body = request().body();

		Survey survey = Json.fromJson(body.asJson(), Survey.class);
		survey.setUsername(getUserName());
		survey.setId(null);
		Survey result = getSurveyService().save(survey);

		return ok(Json.toJson(result));
	}

	public Result survey(String surveyId) {
		Logger.debug("SurveyAPIController.survey surveyId =" + surveyId);
		Survey survey = getSurveyService().getSurvey(surveyId);
		return ok(Json.toJson(survey));
	}

	public Result deleteSurvey(String surveyId) {

		Survey original = getSurveyService().getSurvey(surveyId);
		if (original != null && !original.canUpdate(getUserName())) {
			return forbidden();
		}

		getSurveyService().delete(surveyId);
		return ok();

	}

	public Result surveys() {
		Logger.debug("SurveyAPIController.surveys()");

		return ok(play.libs.Json.toJson(getSurveyService().getAll()));
	}

}
