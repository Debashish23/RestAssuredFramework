package com.spotify.Oauth2.tests;

import com.spotify.Oauth2.api.PlaylistApi;
import com.spotify.Oauth2.api.StatusCodes;
import com.spotify.Oauth2.pojo.Error;
import com.spotify.Oauth2.pojo.Playlist;
import com.spotify.Oauth2.utils.DataLoader;
import com.spotify.Oauth2.utils.Fakerutils;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.spotify.Oauth2.api.StatusCodes.*;
import static com.spotify.Oauth2.utils.Fakerutils.generateDescription;
import static com.spotify.Oauth2.utils.Fakerutils.generateName;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify Oauth2.0")
@Feature("Playlist Creation Apis")
public class PlaylistTests extends BaseTest{

    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @Issue("123567")
    @TmsLink("90909tyyu")
    @Test(description = "Test Will Create Playlist")
    public void AbleToCreatePlaylist(){
//      File file = new File("src"+File.separator+"/main"+File.separator+"/resources"+File.separator+"/playlistbody.json");
        Playlist playlistrequest = playlistrequestbuilder(generateName(),generateDescription(),false);

        Response response = PlaylistApi.post(playlistrequest);
        AssertStatusCode(response.statusCode(), CODE_201.getCode());

        Playlist playlistresponse = response.as(Playlist.class);
        AssertionResponse(playlistrequest,playlistresponse);
    }
    @Test(description = "Test Will Get a Playlist")
    public void AbleToGetAPlaylist(){
        Playlist playlistrequest = playlistrequestbuilder("Younger Brother Hits", "All Hits of Younger Brother",false);
        Response response = PlaylistApi.get(DataLoader.getInstance().getPlaylistId());
        AssertStatusCode(response.statusCode(),CODE_200 .getCode());

        Playlist playlistresponse = response.as(Playlist.class);
        AssertionResponse(playlistresponse,playlistrequest);
    }

    @Test(description = "Test Will Update Playlist")
    public void AbleToUpdatePlaylistName(){
        Playlist playlistrequest = playlistrequestbuilder(generateName(),generateDescription() ,false);
        Response response = PlaylistApi.put(DataLoader.getInstance().UpdatePlaylistId(), playlistrequest);
        assertThat(response.statusCode(),equalTo(CODE_200.getCode()));
    }

    @Test(description = "Test Will Validate Authorization for the  Playlist api")
    public void Unauthorizedcase(){
//        File file = new File("src"+File.separator+"/main"+File.separator+"/resources"+File.separator+"/playlistbody.json");
        Playlist playlistrequest = playlistrequestbuilder(generateName(),generateDescription(),false);

        Response response = PlaylistApi.post("1234xyz",playlistrequest);
            Error responseError = response.as(Error.class);

        AssertError(responseError,CODE_401.getCode(),CODE_401.getMsg());
    }

    @Test(description = "Test Will Validate Bad Request scenario for the  Playlist api")
    public void BadrequestCase(){
        Playlist playlistrequest = playlistrequestbuilder("", generateDescription(),false);

        Response response = PlaylistApi.post(playlistrequest);
         Error responseError = response.as(Error.class);

        AssertError(responseError,CODE_400.getCode(),CODE_400.getMsg());
    }
    @Step
    public Playlist playlistrequestbuilder(String name,String description,boolean _public){
       return Playlist.builder().
                name(name).
                description(description).
                _public(_public).
                build();
    }
    @Step
    public void AssertionResponse(Playlist playlistrequest, Playlist playlistresponse){
        assertThat(playlistrequest.getName(),equalTo(playlistresponse.getName()));
        assertThat(playlistrequest.getDescription(),equalTo(playlistresponse.getDescription()));
        assertThat(playlistrequest.get_public(),equalTo(playlistresponse.get_public()));
    }
    @Step
    public void AssertStatusCode(int expectedstatuscode, int code){
        assertThat(expectedstatuscode,equalTo(code));
    }
    public void AssertError( Error responseError ,int code,String msg){
        assertThat(responseError.getInnerError().getStatus(),equalTo(code));
        assertThat(responseError.getInnerError().getMessage(), equalTo(msg));
    }
}
