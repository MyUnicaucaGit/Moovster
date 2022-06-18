package edu.unicauca.moovster.ui.movies;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.ArrayList;

import edu.unicauca.moovster.R;
import edu.unicauca.moovster.adapter.MovieAdapter;
import edu.unicauca.moovster.movies.Movie;
import edu.unicauca.moovster.movies.Movies;
import edu.unicauca.moovster.movies.VolleyCallBack;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Show_Movie_list#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Show_Movie_list extends Fragment {
    private String query="";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Show_Movie_list() {
        // Required empty public constructor
    }
    public Show_Movie_list(String query) {
        // Required empty public constructor
        this.query=query;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Show_Movie_list.
     */
    // TODO: Rename and change types and number of parameters
    public static Show_Movie_list newInstance(String param1, String param2) {
        Show_Movie_list fragment = new Show_Movie_list();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show__movie_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Movies myMovies = new Movies(getContext());

        if (this.query.equalsIgnoreCase("")){

            try {
                myMovies.getMoviesByPopularity(new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        RecyclerView recyclerView = view.findViewById(R.id.RecyclerMovieList);
                        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                        recyclerView.setAdapter(new MovieAdapter(myMovies.getRequestedList()));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                myMovies.searchMovie(this.query,new VolleyCallBack() {
                    @Override
                    public void onSuccess() {
                        RecyclerView recyclerView = view.findViewById(R.id.RecyclerMovieList);
                        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
                        recyclerView.setAdapter(new MovieAdapter(myMovies.getRequestedList()));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}