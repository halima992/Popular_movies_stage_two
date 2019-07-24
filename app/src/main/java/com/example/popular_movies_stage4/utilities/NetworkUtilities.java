package com.example.popular_movies_stage4.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtilities {
    final static String MoviesDB_URL = "https://api.themoviedb.org/3/movie";
    final static String PARAM_ApiKey = "api_key";
    final static String API_Key = "16c6e216d7ecb60f9173adda634dadac";
    final static String PARAM_Lan = "language";
    final static String reviews = "reviews";
    final static String videos="videos";
    final static String LANGUAGE = "en-US";

//Todo 2 Bulid the url of MoviesDB
    /**
     * Builds the URL acoording to key and language .
     *
     * @param  query keyword that will be queried for.
     * @return The URL to use to query the movieDB server.\
     */
    public static URL buildUrl(String query){
        Uri bulitUri = Uri.parse(MoviesDB_URL).buildUpon()
                .appendEncodedPath(query)
                .appendQueryParameter(PARAM_ApiKey,API_Key)
                .appendQueryParameter(PARAM_Lan,LANGUAGE)
                .build();
        URL url =null;
        try {
            url = new URL(bulitUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;

    }

    //for trailer and reviews
    public static URL buildAnotherUrl(int id,String path){
        Uri bulitUri = Uri.parse(MoviesDB_URL).buildUpon()
                .appendEncodedPath(String.valueOf(id))
                .appendEncodedPath(path)
                .appendQueryParameter(PARAM_ApiKey,API_Key)
                .appendQueryParameter(PARAM_Lan,LANGUAGE)
                .build();
        URL url =null;
        try {
            url = new URL(bulitUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;

    }

    /** get it from sunshine app
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}

