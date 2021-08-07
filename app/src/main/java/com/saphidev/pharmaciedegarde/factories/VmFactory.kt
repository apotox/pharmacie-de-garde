package com.saphidev.pharmaciedegarde.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.saphidev.pharmaciedegarde.repositories.PharmaciesRepo
import com.saphidev.pharmaciedegarde.vm.MainViewModel

class VmFactory(param: PharmaciesRepo) :
    ViewModelProvider.Factory {
    private val mParam: PharmaciesRepo
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel() as T
    }

    init {
        mParam = param
    }
}