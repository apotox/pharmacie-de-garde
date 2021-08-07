package com.saphidev.pharmaciedegarde.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saphidev.pharmaciedegarde.MainActivity
import com.saphidev.pharmaciedegarde.data.Pharmacy
import com.saphidev.pharmaciedegarde.repositories.PharmaciesRepo
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
        val config = MainActivity.configurationRepo?.getConfiguration() ?: return

        if(config.cityId == 0) return

        if(System.currentTimeMillis() - lastRequest < 10000 && lastResult!=null && config.cityId == lastCityId){
            println("use cache")
            result.value = lastResult
            return
        }

        viewModelScope.launch {
            loading.value = true
            val response:List<Pharmacy>? = repo!!.getPharmacies(id)
            loading.value = false
            lastResult = response
            lastRequest = System.currentTimeMillis()
            lastCityId = config.cityId
            result.value = response
        }
    }

    fun getContributors(id: Int){


    }

}