import play.Application;
import play.GlobalSettings;
import play.Logger;

import com.fixit.service.ProjectService;
import com.fixit.service.impl.MongoProjectService;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class Global extends GlobalSettings {

	private Injector injector;

	@Override
	public void onStart(Application app) {
		/*
		injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(ProjectService.class).to(MongoProjectService.class);
			}
		});
		*/
	}

	@Override
	public void onStop(Application app) {
		//Logger.info("Fix-It - Application shutdown...");
	}

	@Override
	public <A> A getControllerInstance(Class<A> controllerClass)
			throws Exception {
		//Logger.info("Fix-It - getControllerInstance controllerClass..."+ controllerClass.getName());
		return injector.getInstance(controllerClass);
	}

}
