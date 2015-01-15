package com.fixit.model;


public class AccountSummary {

	private static final long serialVersionUID = 1L;
	
	public final String fullName;
	public final String userName;
	
	public AccountSummary(User user){
		fullName = user.getProfile().getName();
		userName = user.getUsername();
	}
	
}
