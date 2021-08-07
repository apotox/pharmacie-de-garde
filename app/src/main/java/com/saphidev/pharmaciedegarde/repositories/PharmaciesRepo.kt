package com.saphidev.pharmaciedegarde.repositories

import androidx.lifecycle.LiveData
import com.saphidev.pharmaciedegarde.api.ApiInterface
import com.saphidev.pharmaciedegarde.data.Pharmacy
import retrofit2.Retrofit

class PharmaciesRepo {

    suspend fun getPharmacies(id: Int): List<Pharmacy>? {

       return ApiInterface.api.getPharmacies(id)

    }
}