package com.fixit.service.impl;

import java.util.ArrayList;
import java.util.List;

import play.Logger;

import com.fixit.model.event.Event;
import com.fixit.model.event.Participation;
import com.fixit.service.EventService;

public class MongoEventService implements EventService {

	public static final String USER_NAME = "username";
	public static final String GROUP_ID = "groupId";
	public static final String EVENT_ID = "eventId";
	public static final String STATUS = "status";

	@Override
	public List<Event> getAll() {
		Logger.debug("MongoEventService.getAll()");
		
		return new ArrayList<Event>();
		//return getCollection().find().toArray();
	}

	@Override
	public void delete(String id) {
		Logger.debug("MongoEventService.delete(String id) id=" + id);

		//getCollection().removeById(id);
	}

	@Override
	public String create(Event event) {
		Event result = save(event);
		return result.getId();
	}

	@Override
	public Event save(Event event) {
		return null;
		/*WriteResult<Event, String> result = null;
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

		return event;*/
	}

	@Override
	public Event getEvent(String id) {
		return null;
	/*	Logger.debug("MongoEventService.load(String id) id=" + id);
		Event result = getCollection().findOneById(id);
		return result;*/
	}

	@Override
	public int countEventsByOwner(String username) {
		return 0;
		/*int result = getCollection().find().count();
		Logger.debug("countEventsByOwner(String owner) owner=" + username
				+ "result = " + result);
		return result;*/
	}

	@Override
	public List<Event> getUserEvents(String username, int offset, int length) {
		return new ArrayList<Event>();

	/*	DBCursor<Event> cursor = getCollection().find().is(USER_NAME, username);
		if (offset > 0) {
			cursor.skip(offset);
		}
		if (length > 0) {
			cursor.limit(length);
		}
		return cursor.toArray(MongoDBPersistence.MAX_OBJECT);*/
	}

	@Override
	public List<Event> getGroupEvents(String groupId, int offset, int length) {
		return new ArrayList<Event>();

	/*	DBCursor<Event> cursor = getCollection().find().is(GROUP_ID, groupId);
		if (offset > 0) {
			cursor.skip(offset);
		}
		if (length > 0) {
			cursor.limit(length);
		}
		return cursor.toArray(MongoDBPersistence.MAX_OBJECT);*/
	}

	@Override
	public int countEventsByGroup(String groupId) {
		return 0;
	/*	int result = getCollection().find().is(GROUP_ID, groupId).count();
		Logger.debug("countEventsByGroup(String groupId) owner=" + groupId
				+ "result = " + result);
		return result;*/
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

	@Override
	public List<Participation> getParticipations(String eventId, int offset,
			int length) {
		return new ArrayList<Participation>();

		// TODO Auto-generated method stub
		/*DBCursor<Participation> cursor = getParticipationCollection().find()
				.is(EVENT_ID, eventId);
		if (offset > 0) {
			cursor.skip(offset);
		}
		if (length > 0) {
			cursor.limit(length);
		}
		return cursor.toArray(MongoDBPersistence.MAX_OBJECT);*/
	}

	@Override
	public Participation save(Participation participation) {
		return null;

		/*WriteResult<Participation, String> result = null;
		participation.incrementVersion();
		if (participation.getId() == null) {
			Logger.debug("MongoEventService.save.insert()");
			Participation existing = getParticipation(
					participation.getEventId(), participation.getUsername());
			if (existing != null) {
				Logger.debug("MongoEventService.save.reconcile with id="
						+ existing.id);
				participation.reconcile(existing);
				participation.setModificationDate(new Date());
				result = getParticipationCollection().updateById(
						participation.id, participation);
			} else {
				participation.setCreationDate(new Date());
				participation.setModificationDate(participation.getCreationDate());
				result = getParticipationCollection().insert(participation);
				participation.setId(result.getSavedId());
			}
		} else {
			Logger.debug("MongoEventService.save.updateById(String id) id="
					+ participation.id);
			participation.setModificationDate(new Date());
			result = getParticipationCollection().updateById(participation.id,
					participation);
		}

		return participation;*/
	}

	@Override
	public Participation getParticipation(String participationId) {
		/*Participation result = getParticipationCollection().findOneById(
				participationId);

		return result;*/
		return null;
	}

	@Override
	public Participation getParticipation(String eventId, String username) {
		/*Participation result = null;

		DBCursor<Participation> cursor = getParticipationCollection().find()
				.is(EVENT_ID, eventId).is(USER_NAME, username);
		
		if (cursor.hasNext()){
			result = cursor.next();			
		}

		return result;*/
		return null;
	}

	@Override
	public List<Participation> getUserParticipations(String username) {
		/*DBCursor<Participation> cursor = getParticipationCollection().find()
				.is(USER_NAME, username);
		
		return cursor.toArray(MongoDBPersistence.MAX_OBJECT);*/
		return new ArrayList<Participation>();

	}

	@Override
	public List<Participation> getParticipations(String eventId) {
		/*DBCursor<Participation> cursor = getParticipationCollection().find()
				.is(EVENT_ID, eventId);
		
		return cursor.toArray(MongoDBPersistence.MAX_OBJECT);*/
		return new ArrayList<Participation>();


	}
	
	@Override
	public int countParticipations(String eventId) {
		/*return getParticipationCollection().find()
				.is(EVENT_ID, eventId).is(STATUS, Participation.STATUS_IN).count();*/
		return 0;
	}

}
