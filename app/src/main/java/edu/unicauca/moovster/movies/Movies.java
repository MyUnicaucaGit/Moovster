package edu.unicauca.moovster.movies;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movies {
    private RequestQueue queue;
    private String url = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=f1961361867f54b54c406ca37edb2ed9";
    private JSONArray movies;
    private Context context;

    public Movies(Context context) {
        queue = Volley.newRequestQueue(context);
        // Request a string response from the provided URL.

    }

    public ArrayList<Movie> getMoviesByPopularity() throws JSONException {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("entro");
                        try {
                            JSONObject myJson = new JSONObject(response);
                            movies = myJson.getJSONArray("results");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("fallo");
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        //return  JSONtoMovies(movies);
        return null;

    }

    private ArrayList<Movie> JSONtoMovies(JSONArray arrayMovies) throws JSONException {
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        for (int i = 0; i < arrayMovies.length(); i++) {
            JSONObject object = arrayMovies.getJSONObject(i);
            movieList.add(new Movie(object.getInt("id"),object.getString("title"), object.getDouble("vote_average")));
        }
        return movieList;
    }


}
