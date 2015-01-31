package com.fixit.validation;

import java.util.ArrayList;
import java.util.List;

public class FixitError {

	private String code;
	private String description;
	private List<String> messages = new ArrayList<String>();
	private String url;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	public void addMessage(String message){
		this.messages.add(message);
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
