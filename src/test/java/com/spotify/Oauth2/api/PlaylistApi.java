package com.spotify.Oauth2.api;

import com.spotify.Oauth2.pojo.Playlist;
import com.spotify.Oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import static com.spotify.Oauth2.api.Constants.PLAYLISTS;
import static com.spotify.Oauth2.api.Constants.USERS;
import static com.spotify.Oauth2.api.TokenManager.getToken;

public class PlaylistApi {
    @Step
    public static Response post(Playlist playlistrequest) {
        return Restresource.post(USERS+"/"+ ConfigLoader.getInstance().getUserId() +PLAYLISTS,getToken(),playlistrequest);

    }
    @Step
    public static Response post(String token, Playlist playlistrequest) {
        return Restresource.post(USERS+"/"+ ConfigLoader.getInstance().getUserId() +PLAYLISTS,token,playlistrequest);
    }
    @Step
    public static Response get(String playlistId) {
        return Restresource.get(PLAYLISTS+"/" + playlistId,getToken());
    }

    @Step
    public static Response put(String playlistId, Playlist playlistrequest) {
        return Restresource.put(PLAYLISTS+"/"  + playlistId,getToken(),playlistrequest);
    }
}

