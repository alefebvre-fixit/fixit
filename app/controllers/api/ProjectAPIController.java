package controllers.api;

import java.util.List;

import play.Logger;
import play.libs.Json;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import play.mvc.Security;

import com.fixit.model.Card;
import com.fixit.model.Contribution;
import com.fixit.model.Project;
import com.fixit.model.card.ItemCard;

import controllers.FixItController;
import controllers.Secured;

@Security.Authenticated(Secured.class)
public class ProjectAPIController extends FixItController {

	public static Result projects() {
		Logger.debug("ProjectAPIController.projects()");

		return ok(play.libs.Json.toJson(getProjectService().getAll()));
	}

	public static Result createProject() {
		Logger.debug("ProjectAPIController.createProject()");

		RequestBody body = request().body();

		Project project = Json.fromJson(body.asJson(), Project.class);
		project.username = getUserName();

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

	public static Result projectByOwner(String username) {
		Logger.debug("ProjectAPIController.projectByOwner username ="
				+ username);

		List<Project> projects = getProjectService().loadByOwner(username);
		return ok(Json.toJson(projects));
	}

	public static Result project(String projectId) {
		Logger.debug("ProjectAPIController.project projectId =" + projectId);
		Project project = getProjectService().load(projectId);
		return ok(Json.toJson(project));
	}

	public static Result card(String projectId, String cardId) {
		Logger.debug("ProjectAPIController.project projectId =" + projectId
				+ " cardId=" + cardId);
		Project project = getProjectService().load(projectId);
		return ok(Json.toJson(project.getCard(cardId)));
	}

	public static Result deleteProject(String projectId) {
		Logger.debug("ProjectAPIController.deleteProject projectId ="
				+ projectId);
		getProjectService().delete(projectId);
		return ok();
	}

	public static Result provide(String projectId, String itemId, int quantity) {
		Logger.debug("ProjectAPIController.provide() projectId=" + projectId
				+ " itemId=" + itemId + "quantity" + quantity);

		Project project = getProjectService().load(projectId);

		if (project != null) {
			Card card = project.getCard(itemId);
			if (card instanceof ItemCard) {
				ItemCard item = (ItemCard) card;
				item.contribute(getUserName(), quantity);
			}
		}
		getProjectService().save(project);
		Logger.debug(Json.toJson(project).toString());
		return ok(Json.toJson(project));

	}

	public static Result cancelContribution(String projectId,
			String contributionId) {
		Logger.debug("ProjectAPIController.cancelContribution() projectId="
				+ projectId + " contributionId=" + contributionId);

		Project project = getProjectService().load(projectId);
		if (project != null) {
			if (project.cancelContribution(contributionId)) {
				getProjectService().save(project);
			}
		}
		return ok(Json.toJson(project));
	}

	public static Result contribution(String projectId, String contributionId) {
		Logger.debug("ProjectAPIController.contribution() projectId="
				+ projectId + " contributionId=" + contributionId);

		Project project = getProjectService().load(projectId);
		if (project != null) {
			Contribution contribution = project.getContribution(contributionId);
			if (contribution != null) {
				return ok(Json.toJson(contribution));
			}
		}
		return notFound();
	}

}
