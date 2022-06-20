package edu.unicauca.moovster.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import edu.unicauca.moovster.MainActivity
import edu.unicauca.moovster.adapter.MovieViewHolder
import edu.unicauca.moovster.databinding.FragmentHomeBinding
import edu.unicauca.moovster.db.AdminsSQLHelper
import edu.unicauca.moovster.role.Rol
import edu.unicauca.moovster.ui.carousel.CarouselFragment


class HomeFragment : Fragment() {
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MovieViewHolder>? = null
    private var _binding: FragmentHomeBinding? = null
    private var fragmentAnimacion: Fragment? = null;
    private var fragmentAccion: Fragment? = null;
    private var fragmentDrama: Fragment? = null;
    private var accedido:Boolean=false;

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
        if (!accedido) {
            accedido=true

            for (rol in getRoles()){
                activity?.supportFragmentManager?.beginTransaction()
                    ?.add(edu.unicauca.moovster.R.id.containerHome,
                        CarouselFragment(rol.roleName, rol.roleTitle) as CarouselFragment
                    )
                    ?.commit();
            }
            accedido=false
        }

    }

    private fun getRoles():ArrayList<Rol>{

        var rol:ArrayList<Rol> = ArrayList();
        val admin: AdminsSQLHelper = AdminsSQLHelper(context, "dbMoovster", null, 1)
        val Db = admin.writableDatabase
        val activity = activity as MainActivity?

        val fila = Db.query("Role",null,null,null,null,null,null)

        with(fila) {
            while (moveToNext()) {
                val newRol = Rol(getString(0),getString(2),getString(1))
                rol.add(newRol)
            }
        }

        return rol;
    }
}