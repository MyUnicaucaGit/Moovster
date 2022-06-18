package edu.unicauca.moovster.ui.profile

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import edu.unicauca.moovster.MainActivity
import edu.unicauca.moovster.R

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
        val txtName: TextView = view.findViewById<TextView>(R.id.profile_name)
        val txtEmail: TextView = view.findViewById<TextView>(R.id.profile_email)
        val activity: MainActivity = getActivity() as MainActivity
        /***

        var email:String="pepe@pruebas.com"

        if (email==""){
            Toast.makeText(context,"Redirigir a iniciar sesion", Toast.LENGTH_SHORT).show()
        }
        else{
            val fila: Cursor = activity.getDB().rawQuery("select name, email from User where email = '"+email+"'",null);
            if (fila.moveToFirst()){
                txtName.setText(fila.getString(0))
                txtEmail.setText(fila.getString(1))
                activity.getDB().close()
            }
            else{
                Toast.makeText(context,"No hay usuario", Toast.LENGTH_SHORT).show()
                activity.getDB().close()
            }
        }**/

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
}