package edu.unicauca.moovster.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import android.widget.Switch
import android.widget.TextView
import androidx.fragment.app.Fragment
import edu.unicauca.moovster.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFilters.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFilters : Fragment() {
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
        return inflater.inflate(R.layout.fragment_search_filters, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val yearSearch:SeekBar=view.findViewById(R.id.seekBar);
        val txtYear: TextView=view.findViewById(R.id.txtYearFilter)
        val filterYar:Switch= view.findViewById(R.id.switchYear)
        val searchButton: Button=view.findViewById(R.id.btnSearchFilter)
        val switchList:ArrayList<Switch> = ArrayList();
        val genresList:ArrayList<String> = ArrayList();
        switchList.add( view.findViewById(R.id.switch1))
        switchList.add(  view.findViewById(R.id.switch2))
        switchList.add(  view.findViewById(R.id.switch3))
        switchList.add(  view.findViewById(R.id.switch4) )
        switchList.add(  view.findViewById(R.id.switch5))
        switchList.add( view.findViewById(R.id.switch6))
        switchList.add(  view.findViewById(R.id.switch7))
        switchList.add(  view.findViewById(R.id.switch8))
        switchList.add(  view.findViewById(R.id.switch9))
        switchList.add(  view.findViewById(R.id.switch10))
        switchList.add(  view.findViewById(R.id.switch11))
        switchList.add(  view.findViewById(R.id.switch12))
        switchList.add(  view.findViewById(R.id.switch13))
        switchList.add(  view.findViewById(R.id.switch14))
        switchList.add(  view.findViewById(R.id.switch15))
        switchList.add(  view.findViewById(R.id.switch16))
        switchList.add(  view.findViewById(R.id.switch17))
        switchList.add(  view.findViewById(R.id.switch18))
        yearSearch.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, currentValue: Int, p2: Boolean) {
                txtYear.text=((2022-60)+(currentValue)).toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })
        searchButton.setOnClickListener {
            var yearFilter=0
            if(filterYar.isChecked){yearFilter=txtYear.text.toString().toInt()}

            for (switch in switchList){
                if (switch.isChecked){
                    genresList.add(switch.text.toString())
                }
            }
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.nav_host_fragment_activity_main, Show_Movie_list(genresList,yearFilter))
                ?.commit()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFilters.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFilters().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}