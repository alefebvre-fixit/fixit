package controllers.api;

import java.util.List;

import play.Logger;
import play.libs.Json;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

import com.fixit.model.Contribution;
import com.fixit.model.Project;
import com.fixit.model.card.ContributionFactory;

import controllers.FixItController;

//@Security.Authenticated(Secured.class)
public class ContributionAPIController extends FixItController {

	public static Result getUserContributions(String username) {
		List<Contribution> contributions = getContributionService()
				.getUserContributions(username, -1, -1);
		return ok(Json.toJson(contributions));
	}

	public static Result cancelContribution(String projectId,
			String contributionId) {

		Project project = getProjectService().getProject(projectId);
		Contribution contribution = getContributionService().getContribution(
				contributionId);

		if (contribution != null) {
			project.cancelContribution(contribution);
			getContributionService().deleteContribution(contribution.id);
			//getContributionService().saveContribution(contribution);
			getProjectService().save(project);
		}

		return ok(Json.toJson(project));
	}

	public static Result getContribution(String projectId, String contributionId) {
		Project project = getProjectService().getProject(projectId);
		if (project != null) {
			Contribution contribution = getContributionService()
					.getContribution(contributionId);
			if (contribution != null) {
				return ok(Json.toJson(contribution));
			}
		}
		return notFound();
	}

	public static Result getProjectContributions(String projectId) {
		return ok(Json.toJson(getContributionService().getProjectContributions(
				projectId)));
	}

	public static Result getCardContributions(String projectId, String cardId) {
		return ok(Json.toJson(getContributionService().getCardContributions(
				cardId)));
	}

	public static Result createContribution(String projectId, String cardId,
			String type) {
		Logger.debug("ContributionAPIController.createContribution projectId =" + projectId
				+ " cardId=" + cardId + "type=" + type);
		
		Contribution contribution = ContributionFactory.createContribution(type,
				projectId, cardId, getUserName()); 
		
		if (contribution != null){
			return ok(Json.toJson(ContributionFactory.createContribution(type,
					projectId, cardId, getUserName())));
		}
		return notFound();
	}

	public static Result contribute(String projectId, String cardId) {

		RequestBody body = request().body();
		Contribution contribution = Json.fromJson(body.asJson(),
				Contribution.class);

		Project project = getProjectService().getProject(projectId);
		List<Contribution> userContributions = getContributionService().getUserContributionForCard(getUserName(), cardId);
		if (project != null) {
			if (project.contribute(contribution, userContributions)) {
				getNotificationService().publishNotification(contribution);
				getContributionService().saveContribution(contribution);
				getProjectService().save(project);
			}
		}

		return ok(Json.toJson(project));
	}

}
