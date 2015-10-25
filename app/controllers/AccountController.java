package controllers;

import javax.inject.Inject;

import play.data.Form;
import play.mvc.Result;

import com.fixit.model.User;
import com.fixit.service.UserService;

public class AccountController extends FixItController {

	@Inject
	private UserService userService;

	// Login
	public static class Login {
		public String email;
		public String password;
	}

	static Form<Login> loginForm = Form.form(Login.class);

	public static Result login() {
		return ok(views.html.login.render(loginForm));
	}

	public static Result signup() {
		return ok(views.html.signup.render());
	}

	public static Result logout() {
		session().clear();
		flash("success", "You've been logged out");
		return redirect(routes.AccountController.login());
	}

	public Result authenticate() {
		Form<Login> form = loginForm.bindFromRequest();
		String identifier = form.get().email;
		String password = form.get().password;

		User user = userService.authenticateByEmail(identifier, password);

		if (user == null) {
			userService.authenticateByUserName(identifier, password);
		}

		if (user == null) {
			return forbidden("Invalid password");
		}
		session().clear();
		session(SESSION_ATTRIBUTE_USERNAME, user.getUsername());

		return redirect(routes.ProjectController.projects());
	}

	public static Result userProfile() {
		return ok(views.html.userprofile.render());
	}

}
