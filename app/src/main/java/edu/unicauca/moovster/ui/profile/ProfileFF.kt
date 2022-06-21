package edu.unicauca.moovster.ui.profile

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import edu.unicauca.moovster.MainActivity
import edu.unicauca.moovster.R
import edu.unicauca.moovster.db.AdminsSQLHelper
import edu.unicauca.moovster.role.Rol
import edu.unicauca.moovster.ui.carousel.CarouselFragment
import edu.unicauca.moovster.ui.login.login_or_register

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFF.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFF : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile_f, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val admin = AdminsSQLHelper(context, "dbMoovster", null, 1)
        val Db = admin.writableDatabase
        val activity: MainActivity = getActivity() as MainActivity

        val filaRoles = Db.rawQuery("SELECT rol FROM User_Rol WHERE user_email = '"+activity.getUserEmail()+"'", null)

        if (!activity.isUserLogged()) {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment_activity_main, login_or_register())
                ?.commit()
        }
        val txtName: TextView = view.findViewById<TextView>(R.id.profile_name)
        val txtEmail: TextView = view.findViewById<TextView>(R.id.profile_email)

        val txtRolTitle: TextView = view.findViewById<TextView>(R.id.rolTitle)
        val txtRolDescription: TextView = view.findViewById<TextView>(R.id.rolDescription)
        val imgRol: ImageView = view.findViewById<ImageView>(R.id.rolPicture)
        var imgRolName: String = ""

        if(filaRoles.moveToFirst()){
            for (rol in getRoles()){
                if (filaRoles.getString(0) == rol.rolName) {
                    txtRolTitle.setText(rol.rolTitle)
                    txtRolDescription.setText(rol.rolDescription)
                    imgRolName=rol.rolName;
                    }
                }
            when (imgRolName) {
                "Sustos" -> {
                    imgRol.setImageResource(R.drawable.ghosts)
                }
                "Adventure" -> {
                    imgRol.setImageResource(R.drawable.adventure)
                }
                "Western" ->  {
                    imgRol.setImageResource(R.drawable.western)
                }
            }
            }
        txtName.setText(activity.getUserName())
        txtEmail.setText(activity.getUserEmail())
        activity.getDB().close()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFF.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFF().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun getRoles():ArrayList<Rol>{

        var rol:ArrayList<Rol> = ArrayList();
        val admin: AdminsSQLHelper = AdminsSQLHelper(context, "dbMoovster", null, 1)
        val Db = admin.writableDatabase
        val activity = activity as MainActivity?

        val fila = Db.query("Rol",null,null,null,null,null,null)

        with(fila) {
            while (moveToNext()) {
                val newRol = Rol(getString(0),getString(2),getString(1))
                rol.add(newRol)
            }
        }
        Db.close();
        return rol;
    }
}