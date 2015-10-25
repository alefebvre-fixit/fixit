package com.fixit.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.fixit.model.notification.Notification;

public interface NotificationRepository extends
		MongoRepository<Notification, String> {

	public Page<Notification> findByUsername(String username, Pageable pageable);

	public List<Notification> findByUsername(String username);

}