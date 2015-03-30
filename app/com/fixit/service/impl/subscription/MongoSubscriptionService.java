package com.fixit.service.impl.subscription;

import com.fixit.model.notification.Notification;
import com.fixit.service.ProjectService;
import com.fixit.service.SubscriptionService;
import com.fixit.service.impl.MongoProjectService;
import com.fixit.service.impl.MongoUserService;

public abstract class MongoSubscriptionService {
	
	private static final SubscriptionService cardSubscriptionService = new MongoCardSubscriptionService();
	private static final SubscriptionService projectSubscriptionService = new MongoProjectSubscriptionService();
	private static final SubscriptionService contributionSubscriptionService = new MongoContributionSubscriptionService();
	private static final SubscriptionService favoriteSubscriptionService = new MongoFavoriteSubscriptionService();
	private static final SubscriptionService followersSubscriptionService = new MongoFollowersSubscriptionService();

	private static final ProjectService projectService = new MongoProjectService(new MongoUserService());
	
	protected ProjectService getProjectService(){
		return projectService;
	}

	public static final SubscriptionService getInstance(Notification notification){
		SubscriptionService result = null;
		
		if (Notification.TYPE_CONTRIBUTION.equals(notification.getType())){
			result = contributionSubscriptionService;
		}
		else if (Notification.TYPE_PROJECT.equals(notification.getType())){
			result = projectSubscriptionService;
		}
		else if (Notification.TYPE_CARD.equals(notification.getType())){
			result = cardSubscriptionService;
		}
		else if (Notification.TYPE_FAVORITE.equals(notification.getType())){
			result = favoriteSubscriptionService;
		}
		else if (Notification.TYPE_FOLLOWERS.equals(notification.getType())){
			result = followersSubscriptionService;
		}
		
		return result;
	}
	
}
