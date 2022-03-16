package parallel;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;
import com.spotify.oauth2.utils.FakerUtils;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlayListApi;
import com.spotify.oauth2.pojo.Error;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class PlaylistTests {
	
	Response response;
    Playlist requestPlayList;
    
@Given("User creates a playlist using the Request Specification")
public void shouldBeAbleToCreatePlayList()
{
	Playlist requestPlayList=playListBuilder(FakerUtils.generateName(),FakerUtils.generateDescription(),false);
	this.requestPlayList=requestPlayList;
	Response response = PlayListApi.post(requestPlayList);
	this.response=response;
	
	}
@And("User validates the status code {string} for created Playlist")
public void user_validates_the_status_code_for_created_playlist(String code)
{
	assertStatusCode(response.statusCode(),Integer.parseInt(code));
}

@Then("Validates the response against the request sent for Playlist Creation")
public void userValidatesResponseagainstRequest()
{
	assertPlaylistEqual(response.as(Playlist.class),requestPlayList);	
}
@Then("Validates the error response against {string}  and {string}")
public void validates_the_error_response_against_and(String code, String msg)
{
	assertError(response.as(Error.class),Integer.parseInt(code),msg);
}
@Given("User gets a playlist which he has created earlier")
public void shouldBeAbleToGetPlayList()
{
	Playlist requestPlayList=playListBuilder("Updated Playlist Name","Updated playlist description",false);
	this.requestPlayList=requestPlayList;
	Response response=PlayListApi.get(DataLoader.getInstance().getPlayListId());
	this.response=response;

	
}


@Given("User updates a Playlist which he has created earlier")
public void shouldBeAbleToUpdateAPlayList()
{
	Playlist requestPlayList=playListBuilder(FakerUtils.generateName(),FakerUtils.generateDescription(),false);
	this.requestPlayList=requestPlayList;
	Response response=PlayListApi.update(DataLoader.getInstance().getUpdatePlayListId(), requestPlayList);
	this.response=response;
}

@Given("User tries to create a playlist without name")
public void shouldNotBeAbleToCreatePlayListWithoutName()
{
	Playlist requestPlayList=playListBuilder("",FakerUtils.generateDescription(),false);
	this.requestPlayList=requestPlayList;
	Response response=PlayListApi.post(requestPlayList);
	this.response=response;
	
	assertError(response.as(Error.class),StatusCode.CODE_400.getCode(),StatusCode.CODE_400.getMsg());
	
}
@Given("User tries to create a playlist with incorrect token")
public void shouldNotBeAbleToCreatePlayListWithIncorrectToken()
{  
	Playlist requestPlayList=playListBuilder(FakerUtils.generateName(),FakerUtils.generateDescription(),false);
	this.requestPlayList=requestPlayList;
	Response response=PlayListApi.post("12345",requestPlayList);
	this.response=response;
	
	
}



public Playlist playListBuilder(String name, String description,boolean _public)
{
	return  Playlist.builder().
	name(name).
	description(description).
	_public(_public).build();
	
	
}

public void assertPlaylistEqual(Playlist responsePlayList,Playlist requestPlayList) {
	
	assertThat(responsePlayList.getName(), equalTo(requestPlayList.getName()));
	assertThat(responsePlayList.getDescription(), equalTo(requestPlayList.getDescription()));
	assertThat(responsePlayList.get_public(), equalTo(requestPlayList.get_public()));
}
public void assertStatusCode(int actualStatusCode,int expectedStatusCode)
{
	assertThat(actualStatusCode,equalTo(expectedStatusCode));
}

public void assertError(Error responseError,int expectedStatusCode,String errorMsg) {
	
	assertThat(responseError.getError().getStatus(),equalTo(expectedStatusCode));
	assertThat(responseError.getError().getMessage(),equalTo(errorMsg));
}
}
