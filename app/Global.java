import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import play.Application;
import play.GlobalSettings;
import play.api.mvc.EssentialFilter;

import com.fixit.config.AppConfig;

public class Global extends GlobalSettings {

	@Override
	public void onStop(Application app) {
		// Logger.info("Fix-It - Application shutdown...");
	}

	@Override
	public <T extends EssentialFilter> Class<T>[] filters() {
		// return new Class[] { LogFilter.class };
		return new Class[] {};
	}

	protected ApplicationContext applicationContext;

	@Override
	public void onStart(Application app) {
		/*
		applicationContext = new AnnotationConfigApplicationContext(
				AppConfig.class);
		*/
	}

	@Override
	public final <A> A getControllerInstance(Class<A> clazz) {
		return applicationContext.getBean(clazz);
	}
}
