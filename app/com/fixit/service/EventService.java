package com.fixit.service;

import java.util.List;

import com.fixit.model.event.Event;
import com.fixit.model.event.Participation;

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
	
	public int countParticipations(String eventId);
	
	public Participation save(Participation participation);
	
	public Participation getParticipation(String participationId);
	
	public Participation getParticipation(String eventId, String username);
	
	public List<Participation> getUserParticipations(String username);

	public List<Participation> getParticipations(String eventId);

	
}