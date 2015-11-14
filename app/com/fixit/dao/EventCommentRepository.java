package com.fixit.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.fixit.model.event.EventComment;

public interface EventCommentRepository extends
		MongoRepository<EventComment, String> {

	public Page<EventComment> findByEventId(String eventId, Pageable pageable);

	public List<EventComment> findByEventId(String eventId);

	public int countByEventId(String eventId);

	
}