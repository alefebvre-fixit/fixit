package controllers.test;

import javax.inject.Inject;

import play.mvc.Controller;
import play.mvc.Result;

import com.fixit.model.test.SpringUser;
import com.fixit.service.test.SpringUserService;

public class Test extends Controller {

	@Inject
	private SpringUserService springUserService;

	public Result index() {
		springUserService.addUser(new SpringUser("Saeed", "Zarinfam", "zarin"));
		return ok("insert user saeed successfully!");
	}

}