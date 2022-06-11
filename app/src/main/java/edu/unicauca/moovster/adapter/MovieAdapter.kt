package edu.unicauca.moovster.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.unicauca.moovster.R
import edu.unicauca.moovster.movies.Movie

class MovieAdapter (private val moviesList: ArrayList<Movie>): RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(R.layout.movie_layout,parent,false));
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = moviesList[position]
        holder.render(item)

    }

    override fun getItemCount(): Int {
        return moviesList.size
    }
}