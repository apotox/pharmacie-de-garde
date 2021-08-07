package com.saphidev.pharmaciedegarde.repositories

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.saphidev.pharmaciedegarde.data.City
import java.io.IOException
import kotlin.reflect.typeOf

class CitiesRepo {

    private var _cities: List<City> = listOf()
    private var ctx: Context


    constructor(context: Context){
        ctx = context
    }

    fun getJsonDataFromAsset(context: Context, fileName: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }

    fun loadCities(): List<City>{

        if(_cities.isNotEmpty()) return _cities;

        val json = getJsonDataFromAsset(ctx,"cities.json")

        val gson = Gson()
        val listCitiesType = object : TypeToken<List<City>>() {}.type

        _cities = gson.fromJson(json, listCitiesType)
        return _cities

    }

    fun searchCity(input: String): List<City> {

        //val words = input.split(Regex("[,\\s]"))
        //var input = str.replace(" ","\\s")


        val reg = Regex("${input}",RegexOption.IGNORE_CASE)

        val res = loadCities().filter {

            it.commune_name.contains(reg) || it.commune_name_ascii.contains(reg) ||
                    it.daira_name.contains(reg) ||
            it.wilaya_name.contains(reg) ||
            it.daira_name_ascii.contains(reg)

        }



        return res.take(20)
    }

}