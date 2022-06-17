package edu.unicauca.moovster.ui.profile

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.unicauca.moovster.R
import edu.unicauca.moovster.databinding.FragmentProfileBinding
import edu.unicauca.moovster.db.AdminsSQLHelper

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
                ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val txtName: TextView = view.findViewById<TextView>(R.id.profile_name)
        val txtEmail: TextView = view.findViewById<TextView>(R.id.profile_email)

        super.onViewCreated(view, savedInstanceState)
        val admin : AdminsSQLHelper = AdminsSQLHelper(context, "dbMoovster", null, 1)
        val Db: SQLiteDatabase = admin.writableDatabase

        var email:String="pepe@pruebas.com"

        if (email==""){
            Toast.makeText(context,"Redirigir a iniciar sesion",Toast.LENGTH_SHORT).show()
        }
        else{
            val fila:Cursor = Db.rawQuery("select name, email from User where email = '"+email+"'",null);
            if (fila.moveToFirst()){
                txtName.setText(fila.getString(0))
                txtEmail.setText(fila.getString(1))
                Db.close()
            }
            else{
                Toast.makeText(context,"No hay usuario",Toast.LENGTH_SHORT).show()
                Db.close()
            }
        }

    }
}