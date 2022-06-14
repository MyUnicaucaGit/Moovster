package edu.unicauca.moovster.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jackandphantom.carouselrecyclerview.view.ReflectionImageView
import edu.unicauca.moovster.R
import edu.unicauca.moovster.movies.Movie

class MovieViewHolderCarousel(view:View, listener: MovieAdapterC.onItemClickListener):RecyclerView.ViewHolder(view) {
    val imageC = view.findViewById<ReflectionImageView>(R.id.image_carousel);
    fun render(movie:Movie){
        Glide.with(itemView)
            .load(movie.urlImage)
            .into(imageC);
    }

    init {
        view.setOnClickListener{
            listener.onItemClick(adapterPosition)
        }
    }
}