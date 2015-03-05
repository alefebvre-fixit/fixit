package com.fixit.service;

import java.util.Set;

import com.fixit.model.notification.Notification;

public interface SubscriptionService {

	public abstract Set<String> getSubscribers(Notification notification);

}
