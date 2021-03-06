package com.fixit.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.fixit.model.event.EventComment;

public interface EventCommentRepository extends
		MongoRepository<EventComment, String> {

	public Page<EventComment> findByEventIdOrderByCommentDateDesc(String eventId, Pageable pageable);

	public List<EventComment> findByEventIdOrderByCommentDateDesc(String eventId);

	public int countByEventId(String eventId);

	
}