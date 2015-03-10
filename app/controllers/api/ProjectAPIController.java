package controllers.api;

import java.util.List;

import play.Logger;
import play.libs.Json;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

import com.fixit.model.Card;
import com.fixit.model.Project;
import com.fixit.model.ProjectFactory;
import com.fixit.model.card.CardFactory;

import controllers.FixItController;

//@Security.Authenticated(Secured.class)
public class ProjectAPIController extends FixItController {

	public static Result projects() {
		Logger.debug("ProjectAPIController.projects()");

		return ok(play.libs.Json.toJson(getProjectService().getAll()));
	}

	public static Result createNewProject() {
		Logger.debug("ProjectAPIController.createNewProject()");

		return ok(Json.toJson(ProjectFactory.createProject(getUser())));
	}

	public static Result publishProject(String projectId) {
		Project project = getProjectService().getProject(projectId);
		project.setStatus(Project.STATUS_PUBLISHED);

		project = getProjectService().save(project);

		return ok(Json.toJson(project));
	}

	public static Result createProject() {
		Logger.debug("ProjectAPIController.createProject()");

		RequestBody body = request().body();

		Project project = Json.fromJson(body.asJson(), Project.class);
		project.username = getUserName();
		project.setStatus(Project.STATUS_DRAFT);
		String id = getProjectService().create(project);
		project.id = id;
		return ok(Json.toJson(project));
	}

	public static Result save() {
		Logger.debug("ProjectAPIController.save()");

		RequestBody body = request().body();

		Project project = Json.fromJson(body.asJson(), Project.class);
		project.username = getUserName();

		Project result = getProjectService().save(project);

		return ok(Json.toJson(result));
	}

	public static Result getUserProjects(String username) {
		Logger.debug("ProjectAPIController.projectByOwner username ="
				+ username);
		List<Project> projects = getProjectService().getUserProjects(username,
				-1, -1);
		return ok(Json.toJson(projects));
	}

	public static Result follow(String projectId) {
		Logger.debug("ProjectAPIController.follow projectId =" + projectId);

		getProjectService().follow(getUserName(), projectId);

		return ok(Json.toJson(getProjectService().favorites(getUserName())));
	}

	public static Result unfollow(String projectId) {
		Logger.debug("ProjectAPIController.unfollow projectId =" + projectId);

		getProjectService().unfollow(getUserName(), projectId);

		return ok(Json.toJson(getProjectService().favorites(getUserName())));
	}

	public static Result favorites(String username) {
		Logger.debug("ProjectAPIController.favorites username =" + username);

		return ok(Json.toJson(getProjectService().favorites(username)));
	}

	public static Result project(String projectId) {
		Logger.debug("ProjectAPIController.project projectId =" + projectId);
		Project project = getProjectService().getProject(projectId);
		return ok(Json.toJson(project));
	}

	public static Result card(String projectId, String cardId) {
		Logger.debug("ProjectAPIController.project projectId =" + projectId
				+ " cardId=" + cardId);
		Project project = getProjectService().getProject(projectId);
		return ok(Json.toJson(project.getCard(cardId)));
	}

	public static Result deleteCard(String projectId, String cardId) {
		Logger.debug("ProjectAPIController.project projectId =" + projectId
				+ " cardId=" + cardId);
		Project project = getProjectService().getProject(projectId);
		project.deleteCard(cardId);
		return ok(Json.toJson(getProjectService().save(project)));
	}

	public static Result addCard(String projectId) {

		RequestBody body = request().body();
		Card card = Json.fromJson(body.asJson(), Card.class);

		Logger.debug("ProjectAPIController.addCard projectId =" + projectId
				+ " cardId=" + card.getId());
		Project project = getProjectService().getProject(projectId);
		project.addCard(card);
		project = getProjectService().save(project);
		return ok(Json.toJson(project));
	}

	public static Result updateCard(String projectId, String cardId) {

		RequestBody body = request().body();
		Card card = Json.fromJson(body.asJson(), Card.class);

		Logger.debug("ProjectAPIController.updateCard projectId =" + projectId
				+ " cardId=" + card.getId());
		Project project = getProjectService().getProject(projectId);
		project.addCard(card);
		project = getProjectService().save(project);
		return ok(Json.toJson(project));
	}

	public static Result deleteProject(String projectId) {
		Logger.debug("ProjectAPIController.deleteProject projectId ="
				+ projectId);
		getProjectService().delete(projectId);
		return ok();
	}

	public static Result createCard(String projectId, String type) {
		return ok(Json.toJson(CardFactory.createCard(projectId, type, getUserName())));
	}
	
	public static Result getCardSummary(String projectId, String cardId) {
		return ok(Json.toJson(getCardService().getCardSummary(getUserName(), projectId, cardId)));
	}

}
