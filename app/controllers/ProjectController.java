package controllers;

import play.mvc.Result;
import play.mvc.Security;

import com.fixit.service.ProjectService;
import com.fixit.service.impl.MongoProjectService;
import com.google.inject.Inject;

@Security.Authenticated(Secured.class)
public class ProjectController extends FixItController {

	@Inject
	private static ProjectService projectService = new MongoProjectService();

	public static Result index() {
		return redirect(routes.ProjectController.projects());
	}

	public static Result projects() {

		return ok(views.html.projects.render(projectService.getAll()));
	}

	public static Result user(String username) {
		return ok(views.html.user.render(username));
	}

	public static Result me() {
		return ok(views.html.user.render(getUserName()));
	}

	public static Result editProject(String id) {
		return ok(views.html.projectedit.render(id));
	}

	public static Result viewProject(String id) {
		if (id != null && !"".equals(id)) {
			return ok(views.html.projectview.render(id));
		}
		return TODO;
	}

	public static Result newProject() {
		return ok(views.html.projectedit.render(""));
	}

}
