Feature: Rest API Testing using BDD

Scenario: User creates a Playlist 
Given User creates a playlist using the Request Specification 
And User validates the status code "201" for created Playlist
Then Validates the response against the request sent for Playlist Creation

	

Scenario: User gets a Playlist 
Given User gets a playlist which he has created earlier 
And User validates the status code "200" for created Playlist
Then Validates the response against the request sent for Playlist Creation
	
		
Scenario: User tries to create a Playlist without Name 
Given User tries to create a playlist without name 
And User validates the status code "400" for created Playlist
Then Validates the error response against "400"  and "Missing required field: name"
	

Scenario: User tries to create a Playlist with incorrect token 
Given User tries to create a playlist with incorrect token 
And User validates the status code "401" for created Playlist 
Then Validates the error response against "401"  and "Invalid access token"
	
Scenario: User updates a Playlist  
Given User updates a Playlist which he has created earlier
And User validates the status code "200" for created Playlist
	
	