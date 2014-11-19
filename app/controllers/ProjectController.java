package controllers;

import play.mvc.Result;
import play.mvc.Security;

@Security.Authenticated(Secured.class)
public class ProjectController extends FixItController {


	public static Result index() {
		return redirect(routes.ProjectController.projects());
	}

	public static Result projects() {

		return ok(views.html.projects.render(getProjectService().getAll()));
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
	
	public static Result testProject() {
		return ok(views.html.test.render());
	}

}
