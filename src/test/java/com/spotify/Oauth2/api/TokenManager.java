package com.spotify.Oauth2.api;

import com.spotify.Oauth2.utils.ConfigLoader;
import io.restassured.response.Response;
import java.time.Instant;
import java.util.HashMap;


public class TokenManager {
    private static String access_token;
    private static Instant expiry_time;

    public synchronized static String getToken() {
        try {
            if (access_token == null || Instant.now().isAfter(expiry_time)) {
                System.out.println("Renew Token ...");
                Response response = renewtoken();
                access_token = response.path("access_token");
                int expires_in = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expires_in);
            } else {
                System.out.println("Token is valid ");
            }
        } catch (Exception e) {
            throw new RuntimeException("Renew Token is aborted ");
        }
        return access_token;
    }

    private static Response renewtoken() {
        HashMap<String, String> formparams = new HashMap<>();
        formparams.put("client_id", ConfigLoader.getInstance().getClientId());
        formparams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        formparams.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formparams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());

        Response response = Restresource.postAccount(formparams);

        if (response.statusCode() != 200) {
            throw new RuntimeException("Abort!! Renew Token ");
        }
        return response;

    }
}