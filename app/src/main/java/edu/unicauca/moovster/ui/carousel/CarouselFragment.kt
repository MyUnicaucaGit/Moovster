package edu.unicauca.moovster.ui.carousel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager
import com.jackandphantom.carouselrecyclerview.CarouselRecyclerview
import edu.unicauca.moovster.R
import edu.unicauca.moovster.adapter.MovieAdapterC
import edu.unicauca.moovster.adapter.MovieViewHolder
import edu.unicauca.moovster.databinding.FragmentHomeBinding
import edu.unicauca.moovster.movies.Movie
import edu.unicauca.moovster.movies.Movies
import edu.unicauca.moovster.movies.VolleyCallBack
import edu.unicauca.moovster.ui.home.HomeViewModel
import org.w3c.dom.Text

class CarouselFragment(var gender: String) : Fragment(){

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MovieViewHolder>? = null
    private var _binding: FragmentHomeBinding? = null
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_carousel, container, false)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title : TextView? = view?.findViewById<TextView>(R.id.title_card)
        title?.text=this.gender;
        val myMovies = Movies(context);



        myMovies.getMoviesByGender(listOf(gender), VolleyCallBack {
            val recyclerView = view.findViewById<CarouselRecyclerview>(R.id.recyclerCarousel)
            recyclerView.layoutManager= LinearLayoutManager(context)
            try {
                recyclerView.adapter= MovieAdapterC(myMovies.requestedList)
            }
            catch (e:Exception){}
            recyclerView.set3DItem(true)
            recyclerView.setAlpha(true)
            recyclerView.setInfinite(true)
            myMovies.requestedList

            recyclerView.setItemSelectListener(object : CarouselLayoutManager.OnSelected
            {
                override fun onItemSelected(position: Int) {
                    Toast.makeText(context,myMovies.requestedList[position].urlImage, Toast.LENGTH_LONG);
                }
            })
        })
}}