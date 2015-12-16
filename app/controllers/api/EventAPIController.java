package controllers.api;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.inject.Named;

import play.Logger;
import play.libs.Json;
import play.mvc.Http.RequestBody;
import play.mvc.Result;
import play.mvc.Results;

import com.fixit.model.event.Event;
import com.fixit.model.event.EventComment;
import com.fixit.model.event.EventFactory;
import com.fixit.model.event.Participation;
import com.fixit.model.user.User;

import controllers.YaController;

//@Security.Authenticated(Secured.class)
@Named
public class EventAPIController extends YaController {

	public Result events() {
		Logger.debug("EventAPIController.events()");

		return ok(play.libs.Json.toJson(getEventService().getAll()));
	}

	public Result getEventTimeline() {
		Logger.debug("EventAPIController.getEventTimeline()");
		return ok(play.libs.Json.toJson(getEventService().getEventTimeline()));
	}

	public Result getEventTimelineByGroup(String groupId) {
		Logger.debug("EventAPIController.getEventTimelineByGroup(groupId) groupId="
				+ groupId);
		return ok(play.libs.Json.toJson(getEventService().getEventTimeline(
				groupId)));
	}

	public Result createNewEvent(String groupId) {
		Logger.debug("EventAPIController.createNewEvent() gor groupId"
				+ groupId);

		return ok(Json.toJson(EventFactory.createEvent(getGroupService()
				.getGroup(groupId), getUser())));
	}

	public Result publishEvent(String eventId) {

		Event event = getEventService().getEvent(eventId);

		if (!event.canUpdate(getUserName())) {
			return forbidden();
		}

		event.setStatus(Event.STATUS_PUBLISHED);
		event = getEventService().save(event);

		return ok(Json.toJson(event));
	}

	public Result update(String eventId) {
		Logger.debug("EventAPIController.save()");

		Event original = getEventService().getEvent(eventId);

		if (original != null && !original.canUpdate(getUserName())) {
			return forbidden();
		}

		RequestBody body = request().body();

		Event event = Json.fromJson(body.asJson(), Event.class);
		event.setUsername(getUserName());
		event.setId(eventId);
		Event result = getEventService().save(event);

		return ok(Json.toJson(result));
	}

	public Result create() {
		Logger.debug("EventAPIController.save()");

		RequestBody body = request().body();

		Event event = Json.fromJson(body.asJson(), Event.class);
		event.setUsername(getUserName());
		event.setId(null);
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

		Event original = getEventService().getEvent(eventId);
		if (original != null && !original.canUpdate(getUserName())) {
			return forbidden();
		}

		getEventService().delete(eventId);
		return ok();
	}

	public Result participate(String eventId) {
		Logger.debug("EventAPIController.participate");

		RequestBody body = request().body();
		Participation participation = Json.fromJson(body.asJson(),
				Participation.class);

		Event event = getEventService().getEvent(eventId);
		if (event != null) {
			if (event.accept(participation)) {
				participation.setEventName(event.getName());
				participation.setEventId(event.getId());
				participation = getEventService().save(participation);
			}
		} else {
			Logger.error("Cannot participate to an unknown event. Event Id = "
					+ participation.getEventId());
		}
		return ok(play.libs.Json.toJson(participation));
	}

	public Result generateParticipation(String eventId) {
		Logger.debug("EventAPIController.generateParticipation() eventId = "
				+ eventId);
		Event event = getEventService().getEvent(eventId);
		if (event != null) {
			getEventService().deleteEventParticipations(eventId);

			List<User> users = getUserService().getAll();
			if (users != null) {
				for (User user : users) {
					Logger.debug("User username=|" + user.getUsername() + "|");
					if (user.getUsername() != null
							&& !user.getUsername().equals("")) {
						Participation participation = new Participation(event,
								user);
						int i = ThreadLocalRandom.current().nextInt(1, 6);
						if (i == 1 || i == 2) {
							participation.setStatus(Participation.STATUS_IN);
						} else if (i == 2 || i == 3 || i == 4) {
							participation.setStatus(Participation.STATUS_OUT);
						} else {
							participation.setStatus(Participation.STATUS_RSVP);
						}
						getEventService().save(participation);
					} else {
						getUserService().delete(user.getId());
					}
				}
			}
		}
		return ok();
	}

	public Result participations(String eventId) {
		Logger.debug("EventAPIController.participations()" + eventId);
		return ok(play.libs.Json.toJson(getEventService().getParticipations(
				eventId, 0, -1)));
	}

	public Result participationSummary(String eventId) {
		Logger.debug("EventAPIController.participationSummary()" + eventId);
		return ok(play.libs.Json.toJson(getEventService()
				.getParticipationSummary(eventId)));
	}

	public Result countParticipations(String eventId) {
		Logger.debug("EventAPIController.participations()" + eventId);
		return ok(play.libs.Json.toJson(getEventService().countParticipations(
				eventId)));
	}

	public Result lastParticipations(String eventId) {
		Logger.debug("EventAPIController.participations()" + eventId);
		return ok(play.libs.Json.toJson(getEventService().getParticipations(
				eventId, 0, 5)));
	}

	public Result userParticipations(String username) {
		Logger.debug("EventAPIController.userParticipations()" + username);
		return ok(play.libs.Json.toJson(getEventService()
				.getUserParticipations(username)));
	}

	public Result userParticipation(String username, String eventId) {
		Logger.debug("EventAPIController.userParticipation()" + eventId);

		Participation participation = getEventService().getParticipation(
				eventId, username);
		if (participation == null) {
			participation = new Participation();
			participation.setEventId(eventId);
			participation.setStatus(Participation.STATUS_RSVP);
			participation.setUsername(username);
		}

		return ok(play.libs.Json.toJson(participation));
	}

	public Result comments(String eventId) {
		Logger.debug("EventAPIController.comments for eventId" + eventId);

		List<EventComment> comments = getEventService().getComments(eventId, 0,
				0);

		return ok(play.libs.Json.toJson(comments));
	}

	public Result commentSize(String eventId) {
		Logger.debug("EventAPIController.commentSize for eventId" + eventId);

		return ok(play.libs.Json.toJson(getEventService().getCommentSize(
				eventId)));
	}

	public Result post(String eventId, String content) {
		Logger.debug("EventAPIController.post");

		if (content != null && !"".equals(content)) {
			EventComment comment = new EventComment();
			comment.setEventId(eventId);

			comment.setUsername(getUserName());
			comment.setCommentDate(new Date());
			comment.setContent(content);

			comment = getEventService().saveComments(comment);
			return ok(play.libs.Json.toJson(comment));

		} else {
			return Results.badRequest();
		}

	}

}
