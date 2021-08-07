package com.saphidev.pharmaciedegarde.api

import com.saphidev.pharmaciedegarde.data.Hero
import com.saphidev.pharmaciedegarde.data.Pharmacy
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface GitInterface {

    @GET("/repos/apotox/pharmacie-de-garde/stats/contributors")
    suspend fun getContributors() : List<Hero>

    companion object {

        var BASE_URL = "https://api.github.com/"


        private val retrofit by lazy {
            Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
        }

        val api : GitInterface by lazy {
            retrofit.create(GitInterface::class.java)
        }
    }
}
