package com.saphidev.pharmaciedegarde.adapters

import android.widget.ImageView
import com.saphidev.pharmaciedegarde.data.Pharmacy
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.saphidev.pharmaciedegarde.R

class PharmaciesAdapter(private var dataSet: Array<Pharmacy>) :
    RecyclerView.Adapter<PharmaciesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val pharmacyName: TextView
        val pharmacyHoraire: TextView
        val locationGps: ImageView
        init {
            // Define click listener for the ViewHolder's View.
            pharmacyName = view.findViewById(R.id.pharmacy_name)
            pharmacyHoraire = view.findViewById(R.id.pharmacy_horaire)
            locationGps = view.findViewById(R.id.location_gps)

        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.rv_pharmacy_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
//        viewHolder.textCity.text = dataSet[position].wilaya_name_ascii
//        viewHolder.textDaira.text = dataSet[position].daira_name_ascii
//        viewHolder.textCommune.text = dataSet[position].commune_name_ascii
//
        viewHolder.pharmacyName.text = dataSet[position].name
        viewHolder.itemView.setOnClickListener {

        }
    }

    fun setPharmacis(pharmacies: List<Pharmacy>) {
        dataSet= pharmacies.toTypedArray()
        notifyDataSetChanged()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}

