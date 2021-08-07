package com.saphidev.pharmaciedegarde.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saphidev.pharmaciedegarde.data.Pharmacy
import com.saphidev.pharmaciedegarde.repositories.PharmaciesRepo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainViewModel():ViewModel(){
    private var repo: PharmaciesRepo? = null
    private var lastRequest: Long = 0;
    private var lastCityId: Int = 0;
    private var lastResult:  List<Pharmacy>? = null


    val result: MutableLiveData<List<Pharmacy>> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun initRepo(a: PharmaciesRepo){
        repo = a
    }

    fun getTodayPharmacies(id: Int){


        if(System.currentTimeMillis() - lastRequest < 10000 && lastResult!=null && id == lastCityId){
            println("use cache")
            result.value = lastResult
            return
        }

        val handler = CoroutineExceptionHandler { _, exception ->
            Log.d("Network", "Caught $exception")
            loading.value = false
        }

        viewModelScope.launch(handler) {
            loading.value = true
            val response:List<Pharmacy>? = repo!!.getPharmacies(id)
            loading.value = false
            lastResult = response
            lastRequest = System.currentTimeMillis()
            lastCityId = id
            result.value = response
        }
    }

    fun getContributors(id: Int){


    }

}