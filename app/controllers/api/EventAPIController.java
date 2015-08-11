package controllers.api;

import java.util.List;

import play.Logger;
import play.libs.Json;
import play.mvc.Http.RequestBody;
import play.mvc.Result;

import com.fixit.model.event.Event;
import com.fixit.model.event.EventFactory;

import controllers.YaController;

//@Security.Authenticated(Secured.class)
public class EventAPIController extends YaController {

	public static Result events() {
		Logger.debug("EventAPIController.events()");

		return ok(play.libs.Json.toJson(getEventService().getAll()));
	}

	public static Result createNewEvent(String groupId) {
		Logger.debug("EventAPIController.createNewEvent() gor groupId" + groupId);

		return ok(Json.toJson(EventFactory.createEvent(groupId, getUser())));
	}

	public static Result publishEvent(String eventId) {
		Event event = getEventService().getEvent(eventId);
		event.setStatus(Event.STATUS_PUBLISHED);

		event = getEventService().save(event);

		return ok(Json.toJson(event));
	}

	public static Result createEvent() {
		Logger.debug("EventAPIController.createEvent()");

		RequestBody body = request().body();

		Event event = Json.fromJson(body.asJson(), Event.class);
		event.username = getUserName();
		event.setStatus(Event.STATUS_DRAFT);
		String id = getEventService().create(event);
		event.id = id;
		return ok(Json.toJson(event));
	}

	public static Result save() {
		Logger.debug("EventAPIController.save()");

		RequestBody body = request().body();

		Event event = Json.fromJson(body.asJson(), Event.class);
		event.username = getUserName();

		Event result = getEventService().save(event);

		return ok(Json.toJson(result));
	}

	public static Result getUserEvents(String username) {
		Logger.debug("EventAPIController.eventByOwner username =" + username);
		List<Event> events = getEventService().getUserEvents(username, -1, -1);
		return ok(Json.toJson(events));
	}
	
	public static Result getGroupEvents(String groupId) {
		Logger.debug("EventAPIController.getGroupEvents groupId =" + groupId);
		List<Event> events = getEventService().getGroupEvents(groupId, -1, -1);
		return ok(Json.toJson(events));
	}

	public static Result event(String eventId) {
		Logger.debug("EventAPIController.event eventId =" + eventId);
		Event event = getEventService().getEvent(eventId);
		return ok(Json.toJson(event));
	}

	public static Result deleteEvent(String eventId) {
		Logger.debug("EventAPIController.deleteEvent eventId =" + eventId);
		getEventService().delete(eventId);
		return ok();
	}

}
