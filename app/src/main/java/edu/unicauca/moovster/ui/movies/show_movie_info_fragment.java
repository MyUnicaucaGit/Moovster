package edu.unicauca.moovster.ui.movies;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Calendar;

import edu.unicauca.moovster.MainActivity;
import edu.unicauca.moovster.R;
import edu.unicauca.moovster.movies.Movie;
import edu.unicauca.moovster.movies.Movies;
import edu.unicauca.moovster.movies.VolleyCallBack;
import edu.unicauca.moovster.ui.home.HomeFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link show_movie_info_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class show_movie_info_fragment extends Fragment {
    private int movieId;
    private  int cont=0;
    private String tagForBack="";

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
    public show_movie_info_fragment(int movieId, String tagForBack) {
        this.movieId=movieId;
        this.tagForBack=tagForBack;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Object callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Fragment fragBack = new HomeFragment();
                int idFrag =edu.unicauca.moovster.R.id.containerHome;
                switch(tagForBack){
                    case "Movie_list":
                        fragBack = new Show_Movie_list();
                        idFrag = R.id.fragmentMovies;

                        break;
                    default:
                        idFrag=edu.unicauca.moovster.R.id.containerHome;
                        break;
                }
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(idFrag,fragBack)
                        .commit();

            }

        };

        requireActivity().getOnBackPressedDispatcher().addCallback((OnBackPressedCallback) callback);

        return inflater.inflate(R.layout.fragment_show_movie_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MainActivity main = (MainActivity) getActivity();
        Button viewsButton = view.findViewById(R.id.infoMovieBtnViews);
        MaterialButton star1=view.findViewById(R.id.infoMovieBtnStar1);
        MaterialButton star2=view.findViewById(R.id.infoMovieBtnStar2);
        MaterialButton star3=view.findViewById(R.id.infoMovieBtnStar3);
        MaterialButton star4=view.findViewById(R.id.infoMovieBtnStar4);
        MaterialButton star5=view.findViewById(R.id.infoMovieBtnStar5);
        star1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStars(1);
            }});
        star2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStars(2);
            }});
        star3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStars(3);
            }});
        star4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStars(4);
            }});
        star5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeStars(5);
            }});
        viewsButton.setText("Vista "+cont+" veces");
        viewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // código a ejecutar cuando sea pulsado

                if (main.isUserLogged()){
                    cont++;
                    viewsButton.setText("Vista "+cont+" veces");
                }else{
                    Toast toast = Toast.makeText(getContext(), "No puedes acceder a esta funcionalidad si no estas registrado.", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });
        fillInfo(view);

    }

    private void changeStars(int star){
        MainActivity main = (MainActivity) getActivity();
        if (main.isUserLogged()){

            ArrayList<MaterialButton> stars= new ArrayList<MaterialButton>();
            stars.add(getView().findViewById(R.id.infoMovieBtnStar1));
            stars.add(getView().findViewById(R.id.infoMovieBtnStar2));
            stars.add(getView().findViewById(R.id.infoMovieBtnStar3));
            stars.add(getView().findViewById(R.id.infoMovieBtnStar4));
            stars.add(getView().findViewById(R.id.infoMovieBtnStar5));

            for (int i = 0; i < star; i++) {
                stars.get(i).setIcon(ContextCompat.getDrawable(getContext(),R.drawable.star));
            }
            for (int i = star; i < 5; i++) {
                stars.get(i).setIcon(ContextCompat.getDrawable(getContext(),R.drawable.starempty));
            }

        }else{
            Toast toast = Toast.makeText(getContext(), "No puedes acceder a esta funcionalidad si no estas registrado.", Toast.LENGTH_SHORT);
            toast.show();

        }
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