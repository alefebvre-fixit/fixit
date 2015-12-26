package com.fixit.service.google;


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
