package com.fixit.service.impl.subscription;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fixit.model.notification.Notification;
import com.fixit.service.SubscriptionService;

public class MongoProjectSubscriptionService extends
		MongoSubscriptionService implements SubscriptionService {
	
	@Override
	public Set<String> getSubscribers(Notification notification) {
		
		Set<String> result = new HashSet<String>();
		
		if (Notification.TYPE_PROJECT.equals(notification.getType())) {
			
			//Followers want to receive notification about project they follow
			if (notification.getProjectId() != null){
				List<String> followers = getProjectService().getProjectFollowers(notification.getProjectId());
				if (followers != null && followers.size() > 0){
					result.addAll(followers);
				}				
			}
						
			//TODO Continue implementation
		}
		
		
		return result;
	}

}
