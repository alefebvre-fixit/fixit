package com.fixit.model.event;

import java.util.Date;

import com.fixit.model.group.Group;
import com.fixit.model.user.User;

public class EventFactory {

	public static final Event createEvent(Group group, User user) {
		Event result = new Event();

		result.setUsername(user.getUsername());
		result.setCreationDate(new Date());
		result.setModificationDate(result.getCreationDate());
		result.setCity(user.getProfile().getCity());
		result.setCountry(user.getProfile().getCountry());
		result.setGroupId(group.getId());
		result.setGroupName(group.getName());
		result.setType(group.getType());
		
		return result;
	}

}
