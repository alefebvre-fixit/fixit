package controllers.api;

import play.Logger;
import play.libs.F.Function;
import play.libs.F.Promise;
import play.libs.ws.WS;
import play.libs.ws.WSRequestHolder;
import play.libs.ws.WSResponse;
import play.mvc.Result;

import com.fasterxml.jackson.databind.JsonNode;

import controllers.YaController;

public class GoogleAPIController extends YaController {
	
	//https://developers.google.com/+/web/api/rest/
	//https://developers.google.com/identity/protocols/OAuth2UserAgent#callinganapi
	
	private static final String GOOGLE_PLUS_ENDPOINT = "https://www.googleapis.com/plus/v1/people"; 
	private static final String GOOGLE_ACCESS_TOKEN = "access_token";

	//see here https://developers.google.com/identity/protocols/OAuth2UserAgent#validatetoken
	
	
	public static Promise<Result> test() {
		Logger.debug("googlePlusProfile");
		
		
		Logger.debug("getAccessToken()=" + getAccessToken());
		
		
		WSRequestHolder holder = WS.url(GOOGLE_PLUS_ENDPOINT + "/me");
		//holder.setAuth("user", "password", WSAuthScheme.BASIC).get();
		holder.setQueryParameter(GOOGLE_ACCESS_TOKEN, getAccessToken());
		
		Promise<Result> jsonPromise = holder.get().map(
		        new Function<WSResponse, Result>() {
		            public Result apply(WSResponse response) {
		                JsonNode json = response.asJson();
		                return ok(json);
		            }
		        }
		);
		
		return jsonPromise;
		
	}
	
	
	public static Promise<Result> googlePlusProfile(String userName) {
		Logger.debug("googlePlusProfile/" + userName);
		
		
		Logger.debug(GOOGLE_PLUS_ENDPOINT + "/" + userName);
		
		WSRequestHolder holder = WS.url(GOOGLE_PLUS_ENDPOINT + "/me");
		//holder.setAuth("user", "password", WSAuthScheme.BASIC).get();
		holder.setQueryParameter(GOOGLE_ACCESS_TOKEN, "ya29.iwFCe2Qw8bPAbQefgCdTVk5dqTXqU2Qw0jhE7R0Ni4wm1cJRWPyQmRTTFvequ-yJHi70HdRf0Q2f-Q");
		
		
		Promise<Result> jsonPromise = holder.get().map(
		        new Function<WSResponse, Result>() {
		            public Result apply(WSResponse response) {
		                JsonNode json = response.asJson();
		                return ok(json);
		            }
		        }
		);
		
		return jsonPromise;
		
	}

}
