package com.fixit.service.impl.subscription;

import javax.inject.Inject;
import javax.inject.Named;

import com.fixit.service.EventService;
import com.fixit.service.GroupService;

@Named
public abstract class MongoSubscriptionService {

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
