package edu.unicauca.moovster

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.Window
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import edu.unicauca.moovster.databinding.ActivityMainBinding
import edu.unicauca.moovster.movies.Movies
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide();
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)








        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_home, R.id.navigation_favs, R.id.navigation_profile))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        navView.setItemIconTintList(ColorStateList.valueOf(getResources().getColor(R.color.icons_color)));
        navView.setItemTextColor(ColorStateList.valueOf(getResources().getColor(R.color.icons_color)));
        navView.setBackgroundColor(getResources().getColor(R.color.emotionless_purple));


        val myMovies = Movies(this);
        myMovies.getMoviesByPopularity()


    }

}