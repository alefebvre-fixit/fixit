package com.fixit.service.impl.subscription;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Named;

import com.fixit.model.notification.Notification;
import com.fixit.service.SubscriptionService;

@Named
public class MongoEventSubscriptionService extends
		MongoSubscriptionService implements SubscriptionService {
	
	@Override
	public Set<String> getSubscribers(Notification notification) {
		
		Set<String> result = new HashSet<String>();
		
		if (Notification.TYPE_EVENT.equals(notification.getType())) {
			
			//Followers want to receive notification about group they follow
			List<String> followers = getGroupService().groupFollowerNames(
					notification.getGroupId());
			if (followers != null && followers.size() > 0) {
				result.addAll(followers);
			}
			
						
			//TODO Continue implementation
		}
		
		
		return result;
	}

}
