package com.saphidev.pharmaciedegarde


import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.saphidev.pharmaciedegarde.factories.VmFactory
import com.saphidev.pharmaciedegarde.repositories.ConfigurationRepo
import com.saphidev.pharmaciedegarde.repositories.PharmaciesRepo
import com.saphidev.pharmaciedegarde.vm.MainViewModel


class MainActivity : AppCompatActivity() {



    val config by lazy { ConfigurationRepo(this)  }

    companion object {
        var configurationRepo: ConfigurationRepo? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val navigation = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val navController = findNavController(R.id.navHost)
        navigation.setupWithNavController(navController)

        val vm = ViewModelProvider(this).get(MainViewModel::class.java)
        vm.initRepo(PharmaciesRepo())



        configurationRepo = config

    }
}