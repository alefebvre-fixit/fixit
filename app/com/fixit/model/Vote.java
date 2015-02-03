package com.fixit.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Vote {
	
	private String username;
	private List<String> proposals;
	
	@JsonIgnore
	public String getUsername() {
		return username;
	}
	
	@JsonIgnore
	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<String> getProposals() {
		return proposals;
	}
	
	public void setProposals(List<String> proposals) {
		this.proposals = proposals;
	}
	
}