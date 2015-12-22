package controllers.api;

import javax.inject.Named;

import play.Logger;
import play.libs.Json;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import play.mvc.Security;

import com.fixit.model.survey.Survey;

import controllers.Secured;
import controllers.YaController;

@Named
public class SurveyAPIController extends YaController {

	@Security.Authenticated(Secured.class)
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

	@Security.Authenticated(Secured.class)
	public Result create() {
		Logger.debug("SurveyAPIController.save()");

		RequestBody body = request().body();

		Survey survey = Json.fromJson(body.asJson(), Survey.class);
		survey.setUsername(getUserName());
		survey.setId(null);
		Survey result = getSurveyService().save(survey);

		return ok(Json.toJson(result));
	}

	@Security.Authenticated(Secured.class)
	public Result survey(String surveyId) {
		Logger.debug("SurveyAPIController.survey surveyId =" + surveyId);
		Survey survey = getSurveyService().getSurvey(surveyId);
		return ok(Json.toJson(survey));
	}

	@Security.Authenticated(Secured.class)
	public Result deleteSurvey(String surveyId) {

		Survey original = getSurveyService().getSurvey(surveyId);
		if (original != null && !original.canUpdate(getUserName())) {
			return forbidden();
		}

		getSurveyService().delete(surveyId);
		return ok();

	}

	@Security.Authenticated(Secured.class)
	public Result surveys() {
		Logger.debug("SurveyAPIController.surveys()");

		return ok(play.libs.Json.toJson(getSurveyService().getAll()));
	}

}
