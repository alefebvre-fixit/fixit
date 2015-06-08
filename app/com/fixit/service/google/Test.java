package com.fixit.service.google;

import java.io.InputStreamReader;
import java.util.Collections;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.plus.Plus;
import com.google.api.services.plus.PlusScopes;
import com.google.api.services.plus.model.Person;

public class Test {


	/*
	public static void test(){
		
		// Set up the HTTP transport and JSON factory
		
		String APPLICATION_NAME = "PlusSample";
		java.io.File DATA_STORE_DIR =
		      new java.io.File(System.getProperty("user.home"), ".store/plus_sample");
		FileDataStoreFactory dataStoreFactory;
		
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		
		// Load client secrets
		GoogleClientSecrets clientSecrets = new GoogleClientSecrets();
		clientSecrets.set(fieldName, value);
		


		// Set up authorization code flow
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
		    httpTransport, jsonFactory, clientSecrets,
		    Collections.singleton(PlusScopes.PLUS_ME)).setDataStoreFactory(dataStoreFactory)
		    .build();

		// Authorize
		Credential credential =
		    new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");

		// Set up the main Google+ class
		Plus plus = new Plus.Builder(httpTransport, jsonFactory, credential)
		    .setApplicationName(APPLICATION_NAME)
		    .build();

		// Make a request to access your profile and display it to console
		Person profile = plus.people().get("me").execute();
		System.out.println("ID: " + profile.getId());
		System.out.println("Name: " + profile.getDisplayName());
		System.out.println("Image URL: " + profile.getImage().getUrl());
		System.out.println("Profile URL: " + profile.getUrl());

	}
	*/
	
	
}
