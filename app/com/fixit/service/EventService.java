package com.fixit.service;

import java.util.List;

import com.fixit.model.event.Event;
import com.fixit.model.event.EventComment;
import com.fixit.model.event.EventTimeline;
import com.fixit.model.event.Participation;
import com.fixit.model.event.ParticipationSummary;
import com.fixit.model.user.User;

public interface EventService {

	public List<Event> findAll();

	public EventTimeline findEventTimeline();
	
	public EventTimeline findEventTimeline(String groupId);

	public Event save(Event event);

	public void delete(String id);

	public Event findOne(String id);

	public int countByOwner(String username);

	public List<Event> findUserEvents(String username, int offset, int length);

	public List<Event> findGroupEvents(String groupId, int offset, int length);
	
	public int countByGroup(String groupId);
	
	public String findEventOwner(String eventId);
	
	public List<Participation> findParticipations(String eventId, int offset, int length);
	
	public ParticipationSummary findParticipationSummary(String eventId);
	
	public int countParticipations(String eventId);
	
	public Participation save(Participation participation);
	
	public Participation findOneParticipation(String participationId);
	
	public Participation findOneParticipation(String eventId, String username);
	
	public void deleteParticipation(String id);
	
	public void deleteEventParticipations(String eventId);
	
	public List<Participation> findUserParticipations(String username);

	public List<Participation> findParticipations(String eventId);
	
	public EventComment findOneComment(String commentId);

	public EventComment saveComments(EventComment comment);

	public void deleteComment(String commentId);

	public List<EventComment> findComments(String eventId,
			int offset, int length);
	
	public int countComments(String eventId);
	
	public List<User> findSponsors(String groupId);

}
