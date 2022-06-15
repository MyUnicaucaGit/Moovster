package edu.unicauca.moovster.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jackandphantom.carouselrecyclerview.view.ReflectionImageView
import edu.unicauca.moovster.R
import edu.unicauca.moovster.movies.Movie

class MovieAdapterC (private val moviesList: ArrayList<Movie>): RecyclerView.Adapter<MovieViewHolderCarousel>() {

    private lateinit var mListener : onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolderCarousel {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolderCarousel(layoutInflater.inflate(R.layout.item_view_carousel,parent,false),mListener);
    }

    override fun onFailedToRecycleView(holder: MovieViewHolderCarousel): Boolean {
        return super.onFailedToRecycleView(holder)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun upDateData(moviesList: List<Movie>){
        this.moviesList
        notifyDataSetChanged();
    }

    override fun onBindViewHolder(holder: MovieViewHolderCarousel, position: Int) {
        val item = moviesList[position]
        holder.render(item)
    }


}