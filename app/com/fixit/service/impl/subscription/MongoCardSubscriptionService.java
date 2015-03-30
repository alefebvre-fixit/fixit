package com.fixit.service.impl.subscription;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fixit.model.account.UserCard;
import com.fixit.model.notification.Notification;
import com.fixit.service.SubscriptionService;

public class MongoCardSubscriptionService extends
		MongoSubscriptionService implements SubscriptionService {
	
	@Override
	public Set<String> getSubscribers(Notification notification) {
		
		Set<String> result = new HashSet<String>();
		
		if (Notification.TYPE_CARD.equals(notification.getType())){
			
			//Followers want to receive notification about project they follow
			List<UserCard> followers = getProjectService().projectFollowers(notification.getProjectId());
			if (followers != null && followers.size() > 0){
				for (UserCard userCard : followers) {
					result.add(userCard.userName);
				}
			}

			//TODO Continue implementation
		}
		
		return result;
	}

}
