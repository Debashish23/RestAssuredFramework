package com.spotify.Oauth2.utils;

import java.util.Properties;

public class DataLoader {

    private  final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader(){
        properties = Propertyutils.propertyloader("src/test/resources/data.properties");
    }
    public static DataLoader getInstance(){
        if (dataLoader == null){
             dataLoader = new DataLoader();
        }
        return dataLoader;
    }
    public String getPlaylistId(){
        String prop = properties.getProperty("getPlaylistId");
        if(prop != null) return prop;
        else throw new RuntimeException("getPlaylistId property is not found in the config.properties file");
    }
    public String UpdatePlaylistId(){
        String prop = properties.getProperty("UpdatePlaylistId");
        if(prop != null) return prop;
        else throw new RuntimeException("UpdatePlaylistId property is not found in the config.properties file");
    }
}

