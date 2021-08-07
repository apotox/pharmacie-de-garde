package com.saphidev.pharmaciedegarde.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.saphidev.pharmaciedegarde.MainActivity
import com.saphidev.pharmaciedegarde.R
import com.saphidev.pharmaciedegarde.data.City
import com.saphidev.pharmaciedegarde.data.configuration
import com.saphidev.pharmaciedegarde.repositories.ConfigurationRepo

class CitiesAdapter(private var dataSet: Array<City>) :
    RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textCity: TextView
        val textDaira: TextView
        val textCommune: TextView
        init {
            // Define click listener for the ViewHolder's View.
            textCity = view.findViewById(R.id.text_city)
            textDaira = view.findViewById(R.id.text_daira)
            textCommune = view.findViewById(R.id.text_commune)


        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rv_city_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textCity.text = dataSet[position].wilaya_name_ascii
        viewHolder.textDaira.text = dataSet[position].daira_name_ascii
        viewHolder.textCommune.text = dataSet[position].commune_name_ascii

        viewHolder.itemView.setOnClickListener {
            MainActivity.configurationRepo?.saveConfiguration(configuration(
                cityId = dataSet[position].id,
                city = dataSet[position].wilaya_name_ascii,
                daira = dataSet[position].daira_name_ascii,
                commune = dataSet[position].commune_name_ascii,
            ))

            it.findNavController().navigate(R.id.action_searchFragment_to_homeFragment)
        }
    }

    fun setCities(cities: List<City>) {
        dataSet= cities.toTypedArray()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}

