package com.saphidev.pharmaciedegarde.repositories

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import com.google.gson.Gson
import com.saphidev.pharmaciedegarde.data.configuration
import org.json.JSONException

class ConfigurationRepo(private var context: Context) {

        private  val sharedPrefFile = "pharmacie-de-garde-prefs"
        private  val configurationKey = "configuration_v1"

        fun saveConfiguration(config: configuration) {
            val sharedPreferences =
                context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
            var gson = Gson()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString(configurationKey, gson.toJson(config))
            editor.apply()
            editor.commit()
        }

        fun getConfiguration(): configuration {
            val sharedPreferences =
                context.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

            val str = sharedPreferences.getString(
                configurationKey,
                "{}"
            )
            try {
                var gson = Gson()
                return gson.fromJson(str, configuration::class.java)
            } catch (e: JSONException) {
                return configuration()
            }
        }

}