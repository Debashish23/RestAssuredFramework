package com.spotify.Oauth2.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Propertyutils {
    public static Properties propertyloader(String filepath) {
        Properties properties = new Properties();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(filepath));
            try {
                properties.load(bufferedReader);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to load properties file " + filepath);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("File to load properties file not found " + filepath);
        }
        return properties;
    }
}
