package com.example.popular_movies_stage4.utilities;

import com.example.popular_movies_stage4.module.MovieModule;
import com.example.popular_movies_stage4.module.ReviewModule;
import com.example.popular_movies_stage4.module.trailerModule;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JSONUtilities {

    //final static String USERS_BASE_URL =
    //"https://api.themoviedb.org/3/movie/popular?api_key=16c6e216d7ecb60f9173adda634dadac&language=en-US&page=1";
    public static List<MovieModule> parseMovieJson(String json){
        final String pathOne="https://image.tmdb.org/t/p/w92/";
        final String pathTwo="https://image.tmdb.org/t/p/w500/";

        List<MovieModule> movies = new ArrayList<>();
        try{
            JSONObject movieJson = new JSONObject(json);
            JSONArray movieArray = movieJson.getJSONArray("results");
            for (int i = 0; i < movieArray.length(); i++){
                int id;
                String title,poster,vote,overview,release,poster2;
                title    = movieArray.getJSONObject(i).optString("title");
                poster   = (pathOne+movieArray.getJSONObject(i).optString("poster_path"));
                poster2=(pathTwo+movieArray.getJSONObject(i).optString("poster_path"));
                vote     = movieArray.getJSONObject(i).optString("vote_average");
                overview = movieArray.getJSONObject(i).optString("overview");
                release  = movieArray.getJSONObject(i).optString("release_date");
                id= movieArray.getJSONObject(i).getInt("id");
                //setter
                movies.add(new MovieModule(title ,poster,poster2,vote,overview,release,id));

            }
            return movies;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }
    public static List<ReviewModule> parseReviewJson(String json){

        List<ReviewModule> Review = new ArrayList<>();
               try{
            JSONObject movieJson = new JSONObject(json);
            JSONArray reviewArray = movieJson.getJSONArray("results");
            for (int i = 0; i < reviewArray.length(); i++){
                String author,content;
                author    = reviewArray.getJSONObject(i).optString("author");
                content   = reviewArray.getJSONObject(i).optString("content");

                //setter
                Review.add(new ReviewModule(author ,content));

            }
            return Review;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

        }
        public static List<trailerModule> parseTrailerJson(String json){

        List<trailerModule> Trailer = new ArrayList<>();
               try{
            JSONObject trailerJson = new JSONObject(json);
            JSONArray trailerArray = trailerJson.getJSONArray("results");
            for (int i = 0; i < trailerArray.length(); i++){
                String name,key;
                name    =  trailerArray.getJSONObject(i).optString("name");
                key   =  trailerArray.getJSONObject(i).optString("key");

                //setter
                 Trailer.add(new  trailerModule(name ,key));

            }
            return Trailer;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }

        }


}

