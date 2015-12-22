package controllers;

import play.Logger;
import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security.Authenticator;

public class Secured extends Authenticator {

	@Override
	public String getUsername(Context ctx) {
		Logger.debug("Secured getUsername()" + ctx.session().get(FixItController.SESSION_ATTRIBUTE_USERNAME));
		return ctx.session().get(FixItController.SESSION_ATTRIBUTE_USERNAME);
	}

	@Override
	public Result onUnauthorized(Context ctx) {
		return redirect(routes.AccountController.login());
	}

}
