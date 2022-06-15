package edu.unicauca.moovster.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import edu.unicauca.moovster.R
import edu.unicauca.moovster.movies.Movie

class MovieViewHolder(view:View):RecyclerView.ViewHolder(view) {
    val movieTitle= view.findViewById<TextView>(R.id.movieTitle)
    val movieImage = view.findViewById<ImageView>(R.id.movieImage)
    fun render(movie:Movie){
        movieTitle.text=movie.name
        movieImage.contentDescription= movie.id.toString()
        Glide.with(itemView)
            .load(movie.urlImage)
            .into(movieImage);
    }

}