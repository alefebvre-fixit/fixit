package com.fixit.service;

import java.util.List;

import com.fixit.model.event.Event;
import com.fixit.model.event.EventComment;
import com.fixit.model.event.Participation;
import com.fixit.model.event.ParticipationSummary;

public interface EventService {

	public List<Event> getAll();

	public String create(Event event);

	public Event save(Event event);

	public void delete(String id);

	public Event getEvent(String id);

	public int countEventsByOwner(String username);

	public List<Event> getUserEvents(String username, int offset, int length);

	public List<Event> getGroupEvents(String groupId, int offset, int length);
	
	public int countEventsByGroup(String groupId);
	
	public String getEventOwner(String eventId);
	
	public List<Participation> getParticipations(String eventId, int offset, int length);
	
	public ParticipationSummary getParticipationSummary(String eventId);
	
	public int countParticipations(String eventId);
	
	public Participation save(Participation participation);
	
	public Participation getParticipation(String participationId);
	
	public Participation getParticipation(String eventId, String username);
	
	public void deleteParticipation(String id);
	
	public void deleteEventParticipations(String eventId);
	
	public List<Participation> getUserParticipations(String username);

	public List<Participation> getParticipations(String eventId);
	
	public EventComment getComment(String commentId);

	public EventComment saveComments(EventComment comment);

	public void deleteComment(String commentId);

	public List<EventComment> getComments(String eventId,
			int offset, int length);
	
	public int getCommentSize(String eventId);
	
	
}
