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
import java.util.List;

public class Movies {
    private RequestQueue queue;
    private String url = "https://api.themoviedb.org/3/discover/movie?";
    private JSONArray genresList;

    public ArrayList<Movie> getRequestedList() {
        return requestedList;
    }

    private Context context;
    private ArrayList<Movie> requestedList;
    private Movie requestedMovie;

    public Movies(Context context) {

        try {
            genresList = new JSONArray("[{\"id\":28,\"name\":\"Acción\"},{\"id\":12,\"name\":\"Aventura\"},{\"id\":16,\"name\":\"Animación\"},{\"id\":35,\"name\":\"Comedia\"},{\"id\":80,\"name\":\"Crimen\"},{\"id\":99,\"name\":\"Documental\"},{\"id\":18,\"name\":\"Drama\"},{\"id\":10751,\"name\":\"Familia\"},{\"id\":14,\"name\":\"Fantasía\"},{\"id\":36,\"name\":\"Historia\"},{\"id\":27,\"name\":\"Terror\"},{\"id\":10402,\"name\":\"Música\"},{\"id\":9648,\"name\":\"Misterio\"},{\"id\":10749,\"name\":\"Romance\"},{\"id\":878,\"name\":\"Ciencia ficción\"},{\"id\":10770,\"name\":\"Película de TV\"},{\"id\":53,\"name\":\"Suspense\"},{\"id\":10752,\"name\":\"Bélica\"},{\"id\":37,\"name\":\"Western\"}]");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        queue = Volley.newRequestQueue(context);

    }

    public void getMoviesByPopularity(final VolleyCallBack callBack) throws JSONException {
        String par ="sort_by=popularity.desc";
        StringRequest req = query(par, callBack);
        // Add the request to the RequestQueue.
        queue.add(req);
        //return  JSONtoMovies(movies);
    }

    public void getMovie(int id, final VolleyCallBack callBack) throws JSONException {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.themoviedb.org/3/movie/"+id+"?api_key=f1961361867f54b54c406ca37edb2ed9&language=es",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject movie = new JSONObject(response);
                            requestedMovie=JSONtoMovie(movie);
                            requestedMovie.setDuration(movie.getInt("runtime"));
                            callBack.onSuccess();
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

    }


    public void getMoviesByGender(List<String> genres, VolleyCallBack callBack) throws JSONException {
        String par="";
        for (int i = 0; i < genres.size(); i++) {
            for (int j = 0; j < genresList.length(); j++) {
                JSONObject json = genresList.getJSONObject(j);
                if (genres.get(i).equalsIgnoreCase(json.getString("name"))){
                    par=par+"with_genres="+json.getString("id")+"&";
                }
            }
        }

        StringRequest req = query(par, callBack);
        queue.add(req);

    }

    private StringRequest query(String parameters, final VolleyCallBack callBack){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url+parameters+"&api_key=f1961361867f54b54c406ca37edb2ed9&language=es",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject myJson = new JSONObject(response);
                            JSONArray movies = myJson.getJSONArray("results");
                            requestedList = JSONtoMovies(movies);
                            callBack.onSuccess();
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

        return stringRequest;

    }

    private ArrayList<Movie> JSONtoMovies(JSONArray arrayMovies) throws JSONException {
        ArrayList<Movie> movieList = new ArrayList<Movie>();

        for (int i = 0; i < arrayMovies.length(); i++) {
            JSONObject object = arrayMovies.getJSONObject(i);
            movieList.add(JSONtoMovie(object));
        }
        return movieList;
    }

    private Movie JSONtoMovie(JSONObject movieJson) throws JSONException {
        Movie m = new Movie();
        m.setId(movieJson.getInt("id"));
        m.setName(movieJson.getString("title"));
        m.setRate(movieJson.getDouble("vote_average"));
        m.setUrlImage(movieJson.getString("poster_path"));
        m.setOverview(movieJson.getString("overview"));
        m.setRealease_date(movieJson.getString("release_date"));
        return m;
    }

    public void setRequestedList(ArrayList<Movie> requestedList) {
        this.requestedList = requestedList;
    }

    public Movie getRequestedMovie() {
        return requestedMovie;
    }

    public void setRequestedMovie(Movie requestedMovie) {
        this.requestedMovie = requestedMovie;
    }
}
