package com.fixit.model.notification;

import com.fixit.model.Card;
import com.fixit.model.Contribution;
import com.fixit.model.Favorite;
import com.fixit.model.Project;
import com.fixit.model.event.Participation;
import com.fixit.model.notification.impl.CardNotificationFactory;
import com.fixit.model.notification.impl.CommentNotificationFactory;
import com.fixit.model.notification.impl.ContributionNotificationFactory;
import com.fixit.model.notification.impl.FavoriteNotificationFactory;
import com.fixit.model.notification.impl.ParticipationNotificationFactory;
import com.fixit.model.notification.impl.ProjectNotificationFactory;
import com.fixit.model.project.ProjectComment;

public abstract class NotificationFactory {

	public abstract Notification createNotification(Object object);

	private final static NotificationFactory contributionFactory = new ContributionNotificationFactory();
	private final static NotificationFactory cardFactory = new CardNotificationFactory();
	private final static NotificationFactory projectFactory = new ProjectNotificationFactory();
	private final static NotificationFactory favoriteFactory = new FavoriteNotificationFactory();
	private final static NotificationFactory commentFactory = new CommentNotificationFactory();
	private final static NotificationFactory participationFactory = new ParticipationNotificationFactory();

	public static NotificationFactory getInstance(Object object) {
		NotificationFactory result = null;

		if (object instanceof Contribution) {
			result = contributionFactory;
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

		return result;
	}

}
