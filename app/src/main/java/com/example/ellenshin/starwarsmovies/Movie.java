package com.example.ellenshin.starwarsmovies;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by ellenshin on 2/14/18.
 */

public class Movie {
    // instance variables or fields
    public String title;
    public String imageUrl;
    public String description;
    public String actors;

    // constructor
    // default

    // method
    // static methods that read the json file in and load into movies

    // static method that loads our movies.json using the helper method
    // this method will return an array list of movies constructed from the JSON file
    public static ArrayList<Movie> getMoviesFromFile(String filename, Context context) {
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        // try to read from JSON file
        // get information by using the tags
        // construct a movie Object for each movie in JSON
        // add the object to arraylist
        // return arraylist
        try {
            String jsonString = loadJsonFromAsset("movies.json", context);
            JSONObject json = new JSONObject(jsonString);
            JSONArray movies = json.getJSONArray("movies");

            // for loop to go through each movie in your movies array

            for (int i = 0; i < movies.length(); i++) {
                Movie movie = new Movie();
                JSONObject obj = movies.getJSONObject(i);
                movie.title = obj.getString("title");
                movie.description = obj.getString("description");
                movie.imageUrl = obj.getString("poster");
                movie.actors = obj.getString("main_characters");
                JSONArray mJsonArrayProperty = obj.getJSONArray("main_characters");
                String three_actors = "";

                int j = 0;
                while(j != 3 && j <= mJsonArrayProperty.length()) {
                    three_actors = three_actors + mJsonArrayProperty.get(j) + ", ";
                    j++;
                }

                three_actors = three_actors.substring(0, three_actors.length()-2);
                movie.actors = three_actors;

                movieList.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }

    // helper method that loads from any Json file
    private static String loadJsonFromAsset(String filename, Context context) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        }
        catch (java.io.IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
}
