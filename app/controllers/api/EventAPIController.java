package controllers.api;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import play.Logger;
import play.libs.Json;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

import com.fixit.model.event.Event;
import com.fixit.model.event.EventFactory;
import com.fixit.model.event.Participation;
import com.fixit.service.GroupService;

import controllers.YaController;

//@Security.Authenticated(Secured.class)
@Named
public class EventAPIController extends YaController {

	
	@Inject
	private GroupService groupService;
	
	protected GroupService getGroupService() {
		return groupService;
	}
	
	public Result events() {
		Logger.debug("EventAPIController.events()");

		return ok(play.libs.Json.toJson(getEventService().getAll()));
	}

	public Result createNewEvent(String groupId) {
		Logger.debug("EventAPIController.createNewEvent() gor groupId" + groupId);

		return ok(Json.toJson(EventFactory.createEvent(getGroupService().getGroup(groupId), getUser())));
	}

	public Result publishEvent(String eventId) {
		Event event = getEventService().getEvent(eventId);
		event.setStatus(Event.STATUS_PUBLISHED);

		event = getEventService().save(event);

		return ok(Json.toJson(event));
	}

	public Result createEvent() {
		Logger.debug("EventAPIController.createEvent()");

		RequestBody body = request().body();

		Event event = Json.fromJson(body.asJson(), Event.class);
		event.username = getUserName();
		event.setStatus(Event.STATUS_DRAFT);
		String id = getEventService().create(event);
		event.id = id;
		return ok(Json.toJson(event));
	}

	public Result save() {
		Logger.debug("EventAPIController.save()");

		RequestBody body = request().body();

		Event event = Json.fromJson(body.asJson(), Event.class);
		event.username = getUserName();

		Event result = getEventService().save(event);

		return ok(Json.toJson(result));
	}

	public Result getUserEvents(String username) {
		Logger.debug("EventAPIController.eventByOwner username =" + username);
		List<Event> events = getEventService().getUserEvents(username, -1, -1);
		return ok(Json.toJson(events));
	}
	
	public Result groupEvents(String groupId) {
		Logger.debug("EventAPIController.groupEvents groupId =" + groupId);
		List<Event> events = getEventService().getGroupEvents(groupId, -1, -1);
		return ok(Json.toJson(events));
	}
	
	public Result lastGroupEvents(String groupId) {
		Logger.debug("EventAPIController.lastGroupEvents groupId =" + groupId);
		List<Event> events = getEventService().getGroupEvents(groupId, 0, 5);
		return ok(Json.toJson(events));
	}
	
	public Result eventSizeByGroup(String groupId) {
		Logger.debug("EventAPIController.eventSizeByGroup groupId =" + groupId);
		int events = getEventService().countEventsByGroup(groupId);
		return ok(Json.toJson(events));
	}

	public Result event(String eventId) {
		Logger.debug("EventAPIController.event eventId =" + eventId);
		Event event = getEventService().getEvent(eventId);
		return ok(Json.toJson(event));
	}

	public Result deleteEvent(String eventId) {
		Logger.debug("EventAPIController.deleteEvent eventId =" + eventId);
		getEventService().delete(eventId);
		return ok();
	}
	
	public Result participate(String eventId){
		Logger.debug("EventAPIController.participate");
		
		RequestBody body = request().body();
		Participation participation = Json.fromJson(body.asJson(), Participation.class);

		Event event = getEventService().getEvent(eventId);
		if (event != null){
			if (event.accept(participation)){
				participation.setEventName(event.getName());
				participation.setEventId(event.getId());
				participation = getEventService().save(participation);
			}
		} else {
			Logger.error("Cannot participate to an unknown event. Event Id = " + participation.getEventId());
		}
		return ok(play.libs.Json.toJson(participation));
	}
	
	public Result participations(String eventId) {
		Logger.debug("EventAPIController.participations()" + eventId);
		return ok(play.libs.Json.toJson(getEventService().getParticipations(eventId, 0, -1)));
	}
	
	public Result countParticipations(String eventId) {
		Logger.debug("EventAPIController.participations()" + eventId);
		return ok(play.libs.Json.toJson(getEventService().countParticipations(eventId)));
	}
	
	public Result lastParticipations(String eventId) {
		Logger.debug("EventAPIController.participations()" + eventId);
		return ok(play.libs.Json.toJson(getEventService().getParticipations(eventId, 0, 5)));
	}
	
	public Result userParticipations(String username) {
		Logger.debug("EventAPIController.userParticipations()" + username);
		return ok(play.libs.Json.toJson(getEventService().getUserParticipations(username)));
	}
	
	public Result userParticipation(String username, String eventId) {
		Logger.debug("EventAPIController.userParticipation()" + eventId);
		
		Participation participation = getEventService().getParticipation(eventId, username);
		if (participation == null){
			participation = new Participation();
			participation.setEventId(eventId);
			participation.setStatus(Participation.STATUS_RSVP);
			participation.setUsername(username);
		}
		
		return ok(play.libs.Json.toJson(participation));
	}
	
	
	
	
}
