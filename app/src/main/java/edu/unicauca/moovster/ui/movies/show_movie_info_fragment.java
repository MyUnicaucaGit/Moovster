package edu.unicauca.moovster.ui.movies;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONException;

import java.util.Calendar;

import edu.unicauca.moovster.R;
import edu.unicauca.moovster.movies.Movie;
import edu.unicauca.moovster.movies.Movies;
import edu.unicauca.moovster.movies.VolleyCallBack;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link show_movie_info_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class show_movie_info_fragment extends Fragment {
    private int cont = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public show_movie_info_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment show_movie_info_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static show_movie_info_fragment newInstance(String param1, String param2) {
        show_movie_info_fragment fragment = new show_movie_info_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_show_movie_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Movies gesMovies = new Movies(getContext());
        try {
            gesMovies.getMovie(675353, new VolleyCallBack() {
                @Override
                public void onSuccess() {
                    Movie movie = gesMovies.getRequestedMovie();
                    TextView title = view.findViewById(R.id.infoMovieTxtTitle);
                    TextView dateYear = view.findViewById(R.id.infoMovieTxtYear);
                    TextView overview = view.findViewById(R.id.infoMovieTxtOverview);
                    ImageView image = view.findViewById(R.id.infoMovieImg);
                    TextView duration = view.findViewById(R.id.infoMovieTxtDuration);
                    TextView genres = view.findViewById(R.id.infoMovieTxtGenre);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(movie.getRealease_date());
                    dateYear.setText( String.valueOf( calendar.get(Calendar.YEAR)));
                    title.setText(movie.getName());
                    duration.setText(movie.getDuration() + " min");
                    genres.setText(movie.getGenresString());
                    overview.setText(movie.getOverview());
                    Glide.with(view)
                            .load(movie.getUrlImage())
                            .into(image);


                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}