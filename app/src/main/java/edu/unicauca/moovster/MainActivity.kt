package edu.unicauca.moovster

import android.content.res.ColorStateList
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import edu.unicauca.moovster.databinding.ActivityMainBinding
import edu.unicauca.moovster.db.AdminsSQLHelper
import edu.unicauca.moovster.ui.movies.show_movie_info_fragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var userLogged:Boolean = true;
    private lateinit var admin:AdminsSQLHelper;
    private lateinit var Db: SQLiteDatabase;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide();
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        admin = AdminsSQLHelper(this, "dbMoovster", null, 1)
        Db = admin.writableDatabase

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



    }

    fun peli(view: View) {
        val id :String= view.contentDescription as String;
        val fragmentInformation: Fragment = show_movie_info_fragment(Integer.parseInt(id),"Movie_list")
        this?.supportFragmentManager?.beginTransaction()
            ?.replace(edu.unicauca.moovster.R.id.fragmentMovies,fragmentInformation)
            ?.commit();

    }
    fun isUserLogged(): Boolean {
        return this.userLogged;
    }

    fun setUserLogged(isLogged:Boolean) {
        this.userLogged=isLogged
    }

    fun getDB(): SQLiteDatabase {
        return this.Db;
    }
}