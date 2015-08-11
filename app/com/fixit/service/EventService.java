package com.fixit.service;

import java.util.List;

import com.fixit.model.event.Event;

public interface EventService {

	public List<Event> getAll();

	public String create(Event event);

	public Event save(Event event);

	public void delete(String id);

	public Event getEvent(String id);

	public int countEventsByOwner(String username);

	public List<Event> getUserEvents(String username, int offset, int length);

	public List<Event> getGroupEvents(String groupId, int offset, int length);
	
	public String getEventOwner(String eventId);

}
