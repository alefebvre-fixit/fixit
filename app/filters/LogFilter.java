package filters;

import play.api.mvc.EssentialAction;
import play.api.mvc.EssentialFilter;
import play.Logger;

public class LogFilter implements EssentialFilter {

	@Override
	public EssentialAction apply(EssentialAction action) {
		Logger.info("LogFilter - apply..."+ action.toString());
		return action;
	}

}
