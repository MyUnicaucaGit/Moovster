package edu.unicauca.moovster.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import edu.unicauca.moovster.R
import edu.unicauca.moovster.adapter.MovieViewHolder
import edu.unicauca.moovster.databinding.FragmentHomeBinding
import edu.unicauca.moovster.ui.carousel.CarouselFragment


class HomeFragment : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MovieViewHolder>? = null
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Create new fragment


        val fragmentAnimacion: Fragment = CarouselFragment("Animación");
        val fragmentAccion: Fragment = CarouselFragment("Acción");
        val fragmentDrama: Fragment = CarouselFragment("Suspense");
        activity?.supportFragmentManager?.beginTransaction()
            ?.add(edu.unicauca.moovster.R.id.containerHome,fragmentAnimacion)
            ?.add(edu.unicauca.moovster.R.id.containerHome,fragmentAccion)
            ?.add(edu.unicauca.moovster.R.id.containerHome,fragmentDrama)
            ?.commit();
    }
}