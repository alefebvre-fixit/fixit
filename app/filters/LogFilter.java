package filters;

import play.Logger;
import play.api.mvc.EssentialAction;
import play.api.mvc.EssentialFilter;

public class LogFilter implements EssentialFilter {

	@Override
	public EssentialAction apply(EssentialAction action) {
		Logger.info("LogFilter - apply..."+ action.toString());
		return action;
	}

}
