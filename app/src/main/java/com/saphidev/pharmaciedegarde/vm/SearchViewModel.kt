package com.saphidev.pharmaciedegarde.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.saphidev.pharmaciedegarde.data.City
import com.saphidev.pharmaciedegarde.repositories.CitiesRepo

class SearchViewModel(application: Application):AndroidViewModel(application) {

    val citiesRepo: CitiesRepo by lazy { CitiesRepo(application) }

    var _searchResult: MutableLiveData<List<City>> = MutableLiveData()

    fun SearchForCity(query: String) {
        _searchResult.value = citiesRepo.searchCity(query)
    }

    fun getResult():LiveData<List<City>>{
            return _searchResult
    }
}