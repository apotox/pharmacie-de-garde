package com.saphidev.pharmaciedegarde.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.saphidev.pharmaciedegarde.R
import com.saphidev.pharmaciedegarde.adapters.CitiesAdapter
import com.saphidev.pharmaciedegarde.vm.SearchViewModel


class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)


        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val adapter  = CitiesAdapter(arrayOf())

        val autoComplete = view.findViewById<EditText>(R.id.autoComplete_cities_search)


        val citiesRv = view.findViewById<RecyclerView>(R.id.cities_rv)

        citiesRv.adapter = adapter

        searchViewModel.getResult().observe(this.viewLifecycleOwner,{ list ->
            adapter.setCities(list)
            adapter.notifyDataSetChanged()

        })


        autoComplete.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                println(s.toString())
                searchViewModel.SearchForCity(s.toString())
            }
        })

        return view
    }

}