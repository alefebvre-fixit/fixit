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
		result.setCity(group.getCity());
		result.setCountry(group.getCountry());
		result.setGroupId(group.getId());
		result.setGroupName(group.getName());
		result.setType(group.getType());

		result.setSponsors(group.getSponsors());

		if (result.getSponsors().contains(group.getUsername())) {
			result.getSponsors().add(group.getUsername());
		}

		return result;
	}

}
