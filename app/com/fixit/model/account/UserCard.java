package com.fixit.model.account;

import com.fixit.model.User;

public class UserCard {
	
	public final String fullName;
	public final String userName;
	
	public UserCard(User user){
		fullName = user.getProfile().getName();
		userName = user.getUsername();
	}
	
}
