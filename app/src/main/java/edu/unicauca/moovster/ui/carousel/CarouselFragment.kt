package edu.unicauca.moovster.ui.carousel

import android.os.Bundle
import android.service.autofill.OnClickAction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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
import edu.unicauca.moovster.ui.movies.show_movie_info_fragment
import org.w3c.dom.Text
import java.text.FieldPosition

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
            recyclerView.layoutManager= recyclerView.getCarouselLayoutManager()
            try {
                var adapter = MovieAdapterC(myMovies.requestedList);
                recyclerView.adapter= adapter
                adapter.setOnItemClickListener(object : MovieAdapterC.onItemClickListener{
                    override fun onItemClick(position: Int) {
                        val id = myMovies.requestedList.get(position).id;
                        val fragmentInformation: Fragment = show_movie_info_fragment(id)

                        activity?.supportFragmentManager?.beginTransaction()
                            ?.replace(edu.unicauca.moovster.R.id.containerHome,fragmentInformation)
                            ?.commit()
                    }
                })
            }

            catch (e:Exception){}
            recyclerView.set3DItem(true)
            recyclerView.setAlpha(true)
            recyclerView.setInfinite(true)
            myMovies.requestedList

        })

    }}