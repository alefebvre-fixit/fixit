package com.fixit.model.notification;

import play.Logger;

import com.fixit.model.Card;
import com.fixit.model.Contribution;
import com.fixit.model.Favorite;
import com.fixit.model.Project;
import com.fixit.model.event.Event;
import com.fixit.model.event.Participation;
import com.fixit.model.group.Group;
import com.fixit.model.notification.impl.CardNotificationFactory;
import com.fixit.model.notification.impl.CommentNotificationFactory;
import com.fixit.model.notification.impl.ContributionNotificationFactory;
import com.fixit.model.notification.impl.EventNotificationFactory;
import com.fixit.model.notification.impl.FavoriteNotificationFactory;
import com.fixit.model.notification.impl.GroupNotificationFactory;
import com.fixit.model.notification.impl.ParticipationNotificationFactory;
import com.fixit.model.notification.impl.ProjectNotificationFactory;
import com.fixit.model.notification.impl.SurveyNotificationFactory;
import com.fixit.model.project.ProjectComment;
import com.fixit.model.survey.Survey;

public abstract class NotificationFactory {

	public abstract Notification createNotification(Object object);

	// Fixit
	private final static NotificationFactory contributionFactory = new ContributionNotificationFactory();
	private final static NotificationFactory cardFactory = new CardNotificationFactory();
	private final static NotificationFactory projectFactory = new ProjectNotificationFactory();
	private final static NotificationFactory favoriteFactory = new FavoriteNotificationFactory();
	private final static NotificationFactory commentFactory = new CommentNotificationFactory();

	// Ya
	private final static NotificationFactory participationFactory = new ParticipationNotificationFactory();
	private final static NotificationFactory groupFactory = new GroupNotificationFactory();
	private final static NotificationFactory eventFactory = new EventNotificationFactory();
	private final static NotificationFactory surveyFactory = new SurveyNotificationFactory();

	public static NotificationFactory getInstance(Object object) {
		NotificationFactory result = null;

		if (object instanceof Contribution) {
			result = contributionFactory;
		} else if (object instanceof Group) {
			result = groupFactory;
		} else if (object instanceof Event) {
			result = eventFactory;
		} else if (object instanceof Survey) {
			result = surveyFactory;
		} else if (object instanceof Participation) {
			result = participationFactory;
		} else if (object instanceof Project) {
			result = projectFactory;
		} else if (object instanceof Card) {
			result = cardFactory;
		} else if (object instanceof Favorite) {
			result = favoriteFactory;
		} else if (object instanceof ProjectComment) {
			result = commentFactory;
		}

		if (result != null){
			Logger.debug("NotificationFactory.getInstance(Object object) result=" + result.getClass().getSimpleName());			
		} else {
			Logger.debug("NotificationFactory.getInstance(Object object) result = notfound");		
		}
		
		
		return result;
	}

}
