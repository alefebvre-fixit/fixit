package controllers;

import javax.inject.Inject;
import javax.inject.Named;

import play.mvc.Controller;
import play.mvc.Result;

import com.fixit.model.test.SpringUser;
import com.fixit.service.test.SpringUserService;

import controllers.test.InjectionTester;

@Named
public class SpringController extends Controller {

	@Inject
	private SpringUserService tester;

	public Result index() {
		tester.addUser(new SpringUser("antoine", "lefebvre", "antoinelefebvre"));
		//springUserService.addUser(new SpringUser("Saeed", "Zarinfam", "zarin"));
		return ok("insert user saeed successfully!");
	}


	
}