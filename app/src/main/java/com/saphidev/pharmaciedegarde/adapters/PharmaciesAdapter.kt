package com.saphidev.pharmaciedegarde.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.saphidev.pharmaciedegarde.R
import com.saphidev.pharmaciedegarde.data.Pharmacy
import kotlin.coroutines.coroutineContext


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
        viewHolder.pharmacyName.text = dataSet[position].name
        viewHolder.pharmacyHoraire.text = "${dataSet[position].hourFrom} - ${dataSet[position].hourTo}"
        viewHolder.itemView.setOnClickListener {
            val uri =
                "http://maps.google.com/maps?daddr=" + dataSet[position].location
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
            intent.setPackage("com.google.android.apps.maps");
            startActivity(it.context,intent,null)
        }
    }

    fun setPharmacis(pharmacies: List<Pharmacy>) {
        dataSet= pharmacies.toTypedArray()
        notifyDataSetChanged()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}

