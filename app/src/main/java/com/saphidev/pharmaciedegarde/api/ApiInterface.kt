package com.saphidev.pharmaciedegarde.api

import com.saphidev.pharmaciedegarde.data.Pharmacy
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface ApiInterface {

    @GET("gardes/{id}/today")
    suspend fun getPharmacies(@Path("id") id: Int) : List<Pharmacy>

    companion object {

        var BASE_URL = "https://frozen-earth-99544.herokuapp.com/api/"

        private val retrofit by lazy {
                 Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }

        val api : ApiInterface by lazy {
            retrofit.create(ApiInterface::class.java)
        }
    }
}
