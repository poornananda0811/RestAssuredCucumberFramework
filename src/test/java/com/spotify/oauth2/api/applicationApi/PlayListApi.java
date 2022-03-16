package com.spotify.oauth2.api.applicationApi;
import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.api.Route;
import com.spotify.oauth2.api.TokenManager;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;

import io.restassured.response.Response;

public class PlayListApi {
	
	public static Response post(Playlist requestPlayList) {
		return RestResource.post(Route.USERS+"/"+ConfigLoader.getInstance().getUserId()+Route.PLAYLISTS,TokenManager.getToken(), requestPlayList);
		 			
	}
	
	public static Response post(String Token,Playlist requestPlayList) {
		return RestResource.post(Route.USERS+"/"+ConfigLoader.getInstance().getUserId()+Route.PLAYLISTS,Token, requestPlayList);
		 
	}
	public static Response get(String playListID) {
		return RestResource.get(Route.PLAYLISTS+"/" +playListID,TokenManager.getToken());
		
	}
	
	public static Response update(String playListID,Playlist requestPlayList) {
		return RestResource.update(Route.PLAYLISTS+"/"+ playListID, TokenManager.getToken(), requestPlayList);
		
	}
}
