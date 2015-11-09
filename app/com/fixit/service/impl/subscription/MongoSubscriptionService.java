package com.fixit.service.impl.subscription;

import javax.inject.Inject;
import javax.inject.Named;

import com.fixit.service.EventService;
import com.fixit.service.GroupService;
import com.fixit.service.ProjectService;

@Named
public abstract class MongoSubscriptionService {

	@Inject
	private ProjectService projectService;

	protected ProjectService getProjectService() {
		return projectService;
	}

	@Inject
	private EventService eventService;

	protected EventService getEventService() {
		return eventService;
	}

	@Inject
	private GroupService groupService;

	protected GroupService getGroupService() {
		return groupService;
	}

}
