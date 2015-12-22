package com.fixit.service.impl.subscription;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Named;

import play.Logger;

import com.fixit.model.notification.Notification;
import com.fixit.service.SubscriptionService;

@Named
public class MongoGroupSubscriptionService extends
		MongoSubscriptionService implements SubscriptionService {
	
	@Override
	public Set<String> getSubscribers(Notification notification) {
		
		Set<String> result = new HashSet<String>();
		
		if (Notification.TYPE_GROUP.equals(notification.getType())) {
			
			//Followers want to receive notification about project they follow
			List<String> followers = getGroupService().findFollowerNames(
					notification.getGroupId());
			if (followers != null && followers.size() > 0) {
				result.addAll(followers);
			}
			Logger.debug("MongoGroupSubscriptionService.getSubscribers(Notification notification) result size =" + result.size());	

			//TODO Continue implementation
		} else {
			Logger.debug("MongoGroupSubscriptionService.getSubscribers(Notification notification) notification type is not supported =" + notification.getType());	
		}
		
		
		
		
		return result;
	}

}
