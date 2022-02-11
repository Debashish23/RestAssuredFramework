package com.spotify.Oauth2.api;

import com.spotify.Oauth2.api.SpecBuilder;
import com.spotify.Oauth2.pojo.Playlist;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.util.Map;
import static com.spotify.Oauth2.api.Constants.API;
import static com.spotify.Oauth2.api.Constants.TOKEN;
import static com.spotify.Oauth2.api.SpecBuilder.getAccountrequestSpec;
import static com.spotify.Oauth2.api.SpecBuilder.getresponseSpec;
import static io.restassured.RestAssured.given;

public class Restresource {

    public static Response post(String path,String token, Object playlistrequest) {
        return given(SpecBuilder.getrequestSpec()).
                auth().oauth2(token).
                //header("Authorization","Bearer "+token).
                body(playlistrequest).
                when().post(path).
                then().spec(SpecBuilder.getresponseSpec()).
                extract().response();
    }

    public static Response get(String path, String token) {
        return given(SpecBuilder.getrequestSpec()).
                auth().oauth2(token).
                //header("Authorization","Bearer "+token).
                when().get(path).
                then().spec(SpecBuilder.getresponseSpec()).
                extract().response();

    }

    public static Response put(String path,String token, Object playlistrequest) {
        return given(SpecBuilder.getrequestSpec()).
                auth().oauth2(token).
               // header("Authorization","Bearer "+token).
                body(playlistrequest).
                when().put(path).
                then().spec(SpecBuilder.getresponseSpec()).
                extract().response();
    }

    public static Response postAccount(Map formparams) {
        return  given(getAccountrequestSpec()).
                formParams(formparams).
                when().post(API+TOKEN).
                then().spec(getresponseSpec()).
                log().body().
                extract().response();
    }
}