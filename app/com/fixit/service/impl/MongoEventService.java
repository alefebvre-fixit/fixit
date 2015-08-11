package com.fixit.service.impl;

import java.util.List;

import org.mongojack.DBCursor;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import play.Logger;

import com.fixit.model.event.Event;
import com.fixit.service.EventService;

public class MongoEventService implements EventService {

	public static final String USER_NAME = "username";
	public static final String GROUP_ID = "groupId";

	private JacksonDBCollection<Event, String> getCollection() {
		return MongoDBPersistence.getEventCollection();
	}

	@Override
	public List<Event> getAll() {
		Logger.debug("MongoEventService.getAll()");
		return getCollection().find().toArray();
	}

	@Override
	public void delete(String id) {
		Logger.debug("MongoEventService.delete(String id) id=" + id);

		getCollection().removeById(id);
	}

	@Override
	public String create(Event event) {
		Event result = save(event);
		return result.getId();
	}

	@Override
	public Event save(Event event) {
		WriteResult<Event, String> result = null;
		event.incrementVersion();
		if (event.getId() == null) {
			Logger.debug("MongoEventService.save.insert()");
			result = getCollection().insert(event);
			event.setId(result.getSavedId());
		} else {
			Logger.debug("MongoEventService.save.updateById(String id) id="
					+ event.id);

			result = getCollection().updateById(event.id, event);
		}

		return event;
	}

	@Override
	public Event getEvent(String id) {
		Logger.debug("MongoEventService.load(String id) id=" + id);
		Event result = getCollection().findOneById(id);
		return result;
	}

	@Override
	public int countEventsByOwner(String username) {
		int result = getCollection().find().count();
		Logger.debug("countEventsByOwner(String owner) owner=" + username
				+ "result = " + result);
		return result;
	}

	@Override
	public List<Event> getUserEvents(String username, int offset, int length) {
		DBCursor<Event> cursor = getCollection().find().is(USER_NAME, username);
		if (offset > 0) {
			cursor.skip(offset);
		}
		if (length > 0) {
			cursor.limit(length);
		}
		return cursor.toArray(MongoDBPersistence.MAX_OBJECT);
	}

	@Override
	public List<Event> getGroupEvents(String groupId, int offset, int length) {
		DBCursor<Event> cursor = getCollection().find().is(GROUP_ID, groupId);
		if (offset > 0) {
			cursor.skip(offset);
		}
		if (length > 0) {
			cursor.limit(length);
		}
		return cursor.toArray(MongoDBPersistence.MAX_OBJECT);
	}

	@Override
	public String getEventOwner(String eventId) {
		// TODO Improve implementation by loading only the username

		String result = null;

		Event event = getEvent(eventId);
		if (event != null) {
			result = event.getUsername();
		}

		return result;
	}

}
