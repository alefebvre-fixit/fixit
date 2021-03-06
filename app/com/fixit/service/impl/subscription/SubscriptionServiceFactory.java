package com.fixit.service.impl.subscription;

import javax.inject.Inject;
import javax.inject.Named;

import play.Logger;

import com.fixit.model.notification.Notification;
import com.fixit.service.SubscriptionService;

@Named
public class SubscriptionServiceFactory {

	@Inject
	private MongoFollowersSubscriptionService followersSubscriptionService;

	@Inject
	private MongoParticipationSubscriptionService participationSubscriptionService;
	
	@Inject
	private MongoGroupSubscriptionService groupSubscriptionService;
	
	@Inject
	private MongoEventSubscriptionService eventSubscriptionService;
	
	
	public final SubscriptionService getInstance(Notification notification) {
		SubscriptionService result = null;

		if (Notification.TYPE_FOLLOWERS.equals(notification.getType())) {
			result = followersSubscriptionService;
		} else if (Notification.TYPE_GROUP.equals(notification.getType())) {
			result = groupSubscriptionService;
		} else if (Notification.TYPE_EVENT.equals(notification.getType())) {
			result = eventSubscriptionService;
		} else if (Notification.TYPE_PARTICIPATION.equals(notification.getType())) {
			result = participationSubscriptionService;
		}
		
		if (result != null){
			Logger.debug("SubscriptionServiceFactory.getInstance(Object object) result=" + result.getClass().getSimpleName());			
		} else {
			Logger.debug("SubscriptionServiceFactory.getInstance(Object object) result = notfound");		
		}

		return result;
	}

}
