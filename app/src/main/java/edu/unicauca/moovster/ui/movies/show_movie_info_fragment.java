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
import android.widget.Toast;

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
    private int movieId;
    private  int cont=0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public show_movie_info_fragment() {
        // Required empty public constructor
        this.movieId=152601;
    }
    public show_movie_info_fragment(int movieId) {
        this.movieId=movieId;
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
        Button viewsButton = view.findViewById(R.id.infoMovieBtnViews);
        viewsButton.setText("Vista "+cont+" veces");
        viewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // código a ejecutar cuando sea pulsado
                cont++;
                viewsButton.setText("Vista "+cont+" veces");
            }
        });
        fillInfo(view);

    }

    private  void fillInfo(View view){
        Movies gesMovies = new Movies(getContext());
        try {
            gesMovies.getMovie(this.movieId, new VolleyCallBack() {
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
                            .into(image);     }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}