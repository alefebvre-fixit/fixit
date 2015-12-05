package com.fixit.model.event;

import com.fixit.model.group.Group;
import com.fixit.model.user.User;

public class EventFactory {

	public static final Event createEvent(Group group, User user) {
		Event result = new Event();

		result.setUsername(user.getUsername());
		result.setCity(user.getProfile().getCity());
		result.setCountry(user.getProfile().getCountry());
		result.setGroupId(group.getId());
		result.setGroupName(group.getName());
		result.setType(group.getType());
		
		return result;
	}

}
