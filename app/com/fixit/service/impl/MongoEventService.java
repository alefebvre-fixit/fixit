package com.fixit.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import play.Logger;

import com.fixit.dao.EventRepository;
import com.fixit.dao.ParticipationRepository;
import com.fixit.model.event.Event;
import com.fixit.model.event.Participation;
import com.fixit.service.EventService;
import com.fixit.service.NotificationService;

@Named
public class MongoEventService implements EventService {

	public static final String USER_NAME = "username";
	public static final String GROUP_ID = "groupId";
	public static final String EVENT_ID = "eventId";
	public static final String STATUS = "status";

	@Inject
	EventRepository eventRepository;

	@Inject
	ParticipationRepository participationRepository;
	
	@Inject
	private NotificationService notificationService;

	public NotificationService getNotificationService() {
		return notificationService;
	}

	@Override
	public List<Event> getAll() {
		Logger.debug("MongoEventService.getAll()");
		return eventRepository.findAll();
	}

	@Override
	public void delete(String id) {
		Logger.debug("MongoEventService.delete(String id) id=" + id);
		eventRepository.delete(id);
	}

	@Override
	public String create(Event event) {
		Event result = save(event);
		return result.getId();
	}

	@Override
	public Event save(Event event) {		
		getNotificationService().publishNotification(event);
		Event result =  eventRepository.save(event);
		return result;
	}

	@Override
	public Event getEvent(String id) {
		return eventRepository.findOne(id);
	}

	@Override
	public int countEventsByOwner(String username) {
		return eventRepository.countByUsername(username);
	}

	@Override
	public List<Event> getUserEvents(String username, int offset, int length) {
		List<Event> result = null;

		if (length > 0) {
			Page<Event> pages = eventRepository.findByUsername(username,
					new PageRequest(offset, length));
			result = pages.getContent();
		} else {
			result = eventRepository.findAll();
		}

		return result;
	}

	@Override
	public List<Event> getGroupEvents(String groupId, int offset, int length) {
		List<Event> result = null;

		if (length > 0) {
			Page<Event> pages = eventRepository.findByGroupId(groupId,
					new PageRequest(offset, length));
			result = pages.getContent();
		} else {
			result = eventRepository.findAll();
		}

		return result;
	}

	@Override
	public int countEventsByGroup(String groupId) {
		return eventRepository.countByGroupId(groupId);
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
		List<Participation> result = null;

		if (length > 0) {
			Page<Participation> pages = participationRepository.findByEventId(
					eventId, new PageRequest(offset, length));
			result = pages.getContent();
		} else {
			result = participationRepository.findAll();
		}

		return result;
	}

	@Override
	public Participation save(Participation participation) {

		Participation result = null;
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
				result = participationRepository.save(participation);
			} else {
				participation.setCreationDate(new Date());
				participation.setModificationDate(participation
						.getCreationDate());
				result = participationRepository.save(participation);
			}
		} else {
			Logger.debug("MongoEventService.save.updateById(String id) id="
					+ participation.id);
			participation.setModificationDate(new Date());
			result = participationRepository.save(participation);
		}
		
		getNotificationService().publishNotification(participation);

		return result;
	}

	@Override
	public Participation getParticipation(String participationId) {
		return participationRepository.findOne(participationId);
	}

	@Override
	public Participation getParticipation(String eventId, String username) {
		Participation result = null;

		List<Participation> pages = participationRepository
				.findByEventIdAndUsername(eventId, username);

		if (pages != null && pages.size() > 0) {
			result = pages.get(0);
		}

		return result;
	}

	@Override
	public List<Participation> getUserParticipations(String username) {
		return participationRepository.findByUsername(username);
	}

	@Override
	public List<Participation> getParticipations(String eventId) {
		return participationRepository.findByEventId(eventId);
	}

	@Override
	public int countParticipations(String eventId) {
		return participationRepository.countByEventIdAndStatus(eventId,
				Participation.STATUS_IN);
	}

}
