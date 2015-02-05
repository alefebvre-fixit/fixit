package controllers.api;

import java.util.Arrays;
import java.util.List;

import play.Logger;
import play.libs.Json;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import play.mvc.Security;

import com.fixit.model.Card;
import com.fixit.model.Contribution;
import com.fixit.model.Project;
import com.fixit.model.ProjectFactory;
import com.fixit.model.Votable;
import com.fixit.model.Vote;
import com.fixit.model.card.CardFactory;
import com.fixit.model.card.ItemCard;

import controllers.FixItController;
import controllers.Secured;

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
		Project project = getProjectService().load(projectId);
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
	
	public static Result deleteCard(String projectId, String cardId) {
		Logger.debug("ProjectAPIController.project projectId =" + projectId
				+ " cardId=" + cardId);
		Project project = getProjectService().load(projectId);
		project.deleteCard(cardId);
		return ok(Json.toJson(getProjectService().save(project)));
	}
	
	
	public static Result addCard(String projectId){
		
		RequestBody body = request().body();
		Card card = Json.fromJson(body.asJson(), Card.class);

		Logger.debug("ProjectAPIController.addCard projectId =" + projectId
				+ " cardId=" + card.id);
		Project project = getProjectService().load(projectId);
		project.addCard(card);
		project = getProjectService().save(project);
		return ok(Json.toJson(project));
	}
	
	public static Result updateCard(String projectId, String cardId){
		
		RequestBody body = request().body();
		Card card = Json.fromJson(body.asJson(), Card.class);

		Logger.debug("ProjectAPIController.updateCard projectId =" + projectId
				+ " cardId=" + card.id);
		Project project = getProjectService().load(projectId);
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

	public static Result provide(String projectId, String itemId, int quantity) {
		Logger.debug("ProjectAPIController.provide() projectId=" + projectId
				+ " itemId=" + itemId + "quantity" + quantity);

		Project project = getProjectService().load(projectId);

		if (project != null) {
			Card card = project.getCard(itemId);
			if (card instanceof ItemCard) {
				ItemCard item = (ItemCard) card;
				item.provide(getUserName(), quantity);
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

	public static Result createCard(String projectId, String type){
		
		Card card = CardFactory.createCard(type);
		Logger.debug("ProjectAPIController.createCard type =" + type);
		
		return ok(Json.toJson(card));
	}
	
	
	public static Result vote(String projectId, String itemId) {
		Logger.debug("ProjectAPIController.vote() projectId=" + projectId
				+ " itemId=" + itemId);

		RequestBody body = request().body();
		Vote vote = Json.fromJson(body.asJson(), Vote.class);
		vote.setUsername(getUserName());
		
		Project project = getProjectService().load(projectId);

		if (project != null) {
			Card card = project.getCard(itemId);
			if (card instanceof Votable) {
				Votable votable = (Votable) card;
				votable.submit(vote);
			}
		}
		getProjectService().save(project);
		Logger.debug(Json.toJson(project).toString());
		return ok(Json.toJson(project));

	}
	
	public static Result voteSample(){
		
		Vote vote = new Vote();
		vote.setProposals(Arrays.asList("AAA", "BBB"));
		
		return ok(Json.toJson(vote));
	}
	
}
