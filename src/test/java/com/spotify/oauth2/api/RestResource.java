package com.spotify.oauth2.api;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import com.spotify.oauth2.api.SpecBuilder;
import io.restassured.response.Response;

public class RestResource {
	
	public static Response post(String path,String token,Object requestPlayList) {
		 return given(SpecBuilder.getRequestSpec()).body(requestPlayList).
				 auth().oauth2(token).
				when().
				post(path).
				then().
				spec(SpecBuilder.getResponseSpec()).
				extract().
				response();
	}
	
	public static Response postAccount(HashMap<String,String> formParams) {
		
		return given(SpecBuilder.getAccountRequestSpec()).
		formParams(formParams).
		when().post(Route.API+Route.TOKEN).
		then().spec(SpecBuilder.getResponseSpec()).
		extract()
		.response();
	}
	
	public static Response get(String path,String token) {
		 return given(SpecBuilder.getRequestSpec()).
				 auth().oauth2(token).
				when().
				get(path).
				then().
				spec(SpecBuilder.getResponseSpec()).
				extract().
				response();
	}
	
	public static Response update(String path,String token,Object requestPlayList) {
		return given(SpecBuilder.getRequestSpec()).body(requestPlayList).
				auth().oauth2(token).
		when().
		put(path).
		then().
		spec(SpecBuilder.getResponseSpec()).extract().response();
	}
}
