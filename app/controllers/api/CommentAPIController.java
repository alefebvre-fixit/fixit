package controllers.api;

import java.util.Date;
import java.util.List;

import play.Logger;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;

import com.fixit.model.project.ProjectComment;

import controllers.FixItController;
import controllers.Secured;

public class CommentAPIController extends FixItController {

	@Security.Authenticated(Secured.class)
	public static Result comments(String projectId) {
		Logger.debug("NotificationAPIController.comments");

		List<ProjectComment> comments = getCommentService().getProjectComments(
				projectId, 0, 0);

		return ok(play.libs.Json.toJson(comments));
	}

	@Security.Authenticated(Secured.class)
	public static Result commentSize(String projectId) {
		Logger.debug("NotificationAPIController.commentSize");

		return ok(play.libs.Json.toJson(getCommentService()
				.getProjectCommentSize(projectId)));
	}

	@Security.Authenticated(Secured.class)
	public static Result post(String projectId, String content) {
		Logger.debug("NotificationAPIController.comments");

		if (content != null && !"".equals(content)) {
			ProjectComment comment = new ProjectComment();
			comment.setProjectId(projectId);
			comment.setUsername(getUserName());
			comment.setCommentDate(new Date());
			comment.setContent(content);

			comment = getCommentService().saveComments(comment);
			return ok(play.libs.Json.toJson(comment));

		} else {
			return Results.badRequest();
		}

	}

}
