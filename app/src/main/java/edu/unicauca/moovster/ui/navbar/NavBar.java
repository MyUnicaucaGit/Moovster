package edu.unicauca.moovster.ui.navbar;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import edu.unicauca.moovster.MainActivity;
import edu.unicauca.moovster.R;
import edu.unicauca.moovster.ui.home.HomeFragment;
import edu.unicauca.moovster.ui.login.logIn;
import edu.unicauca.moovster.ui.login.login_or_register;
import edu.unicauca.moovster.ui.movies.SearchFilters;
import edu.unicauca.moovster.ui.movies.Show_Movie_list;
import edu.unicauca.moovster.ui.profile.ProfileFF;
import edu.unicauca.moovster.ui.profile.ProfileFragment;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavBar#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavBar extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NavBar() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NavBar.
     */
    // TODO: Rename and change types and number of parameters
    public static NavBar newInstance(String param1, String param2) {
        NavBar fragment = new NavBar();
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
        return inflater.inflate(R.layout.fragment_nav_bar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnUser = view.findViewById(R.id.btnNBUser);
        Button btnFilter = view.findViewById(R.id.btnFilter);
        SearchView search  = view.findViewById(R.id.searchViewMovies);
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity  d= (MainActivity) getActivity();
                if(d.isUserLogged()){
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment_activity_main,new ProfileFF()).addToBackStack(null)
                            .commit();
                }else{
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.nav_host_fragment_activity_main,new login_or_register()).addToBackStack(null)
                            .commit();
                }

            }
        });
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main,new Show_Movie_list(query))
                        .commit();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main,new SearchFilters()).addToBackStack(null)
                        .commit();
            }
        });

    }
}