package com.saphidev.pharmaciedegarde.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.saphidev.pharmaciedegarde.MainActivity
import com.saphidev.pharmaciedegarde.R
import com.saphidev.pharmaciedegarde.adapters.PharmaciesAdapter
import com.saphidev.pharmaciedegarde.data.Pharmacy
import com.saphidev.pharmaciedegarde.vm.MainViewModel
import kotlinx.android.synthetic.main.rv_pharmacy_item.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class HomeFragment : Fragment() {



    private var pharmaciesRv: RecyclerView? = null
    private var pharmaciesAdapter: PharmaciesAdapter?=null
    private var vm: MainViewModel? = null
    private var currentDate: String = ""



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

         currentDate = SimpleDateFormat("dd/MM/yyyy").format(Date())
        view.findViewById<TextView>(R.id.text_date)?.text = currentDate


        pharmaciesAdapter = PharmaciesAdapter(arrayOf())
        pharmaciesRv = view.findViewById<RecyclerView>(R.id.pharmacies_rv)
        pharmaciesRv?.adapter = pharmaciesAdapter;

        try {
            vm = ViewModelProvider(this.requireActivity()).get(MainViewModel::class.java)
        }catch (e: Exception){
            System.out.println(e.message)
        }

        vm?.result?.observe(this.requireActivity(), {
            if(it != null){
                pharmaciesAdapter?.setPharmacis(it)
            }
        })


        vm?.loading?.observe(this.requireActivity(), {
            if(it){
                view.findViewById<RecyclerView>(R.id.pharmacies_rv).visibility = View.INVISIBLE
                view.findViewById<ProgressBar>(R.id.progress_circular).visibility = View.VISIBLE
            }else{
                view.findViewById<RecyclerView>(R.id.pharmacies_rv).visibility = View.VISIBLE
                view.findViewById<ProgressBar>(R.id.progress_circular).visibility = View.INVISIBLE
            }
        })

        return  view
    }


    private fun update(){
        if(view == null) return
        val config = MainActivity.configurationRepo?.getConfiguration()
        if(config!=null){
            requireView().findViewById<TextView>(R.id.text_location)?.text = "${config.city}, ${config.daira}, ${config.commune}"
            vm?.getTodayPharmacies(config.cityId)
        }



    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        update()

    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }

}