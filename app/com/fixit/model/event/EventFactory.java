package com.fixit.model.event;

import com.fixit.model.User;

public class EventFactory {

	public static final Event createEvent(String groupId, User user) {
		Event result = new Event();

		result.setUsername(user.getUsername());
		result.setCity(user.getProfile().getCity());
		result.setCountry(user.getProfile().getCountry());
		result.setGroupId(groupId);

		return result;
	}

}
