package com.fixit.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.fixit.model.event.Event;
import com.fixit.model.event.Participation;

public interface ParticipationRepository extends MongoRepository<Participation, String> {

	public int countByUsername(String username);

	public Page<Event> findByUserName(String username, Pageable pageable);
	
	public int countByGroupId(String groupId);
	
	public Page<Participation> findByEventId(String eventId, Pageable pageable);
	
	public List<Participation> findByEventIdAndUsername(String eventId, String username);


}