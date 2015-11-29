package com.fixit.service.impl.subscription;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Named;

import com.fixit.model.notification.Notification;
import com.fixit.service.SubscriptionService;


@Named
public class MongoContributionSubscriptionService extends
		MongoSubscriptionService implements SubscriptionService {

	
	
	@Override
	public Set<String> getSubscribers(Notification notification) {
		
		Set<String> result = new HashSet<String>();
		
		if (notification.getProjectId() != null){

			//Owner of the project want to receive notifications
			String owner = getProjectService().getProjectOwner(notification.getProjectId());
			if (owner != null){
				result.add(owner);			
			}
			
			//Followers want to receive notification about project they follow
			List<String> followers = getProjectService().projectFollowerNames(
					notification.getProjectId());
			if (followers != null && followers.size() > 0) {
				result.addAll(followers);
			}
			
		}

		//TODO Continue implementation
		
		
		return result;
	}

}
