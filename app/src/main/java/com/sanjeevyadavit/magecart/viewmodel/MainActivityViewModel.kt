package com.sanjeevyadavit.magecart.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.model.LoginBodyRequest
import com.sanjeevyadavit.magecart.model.StoreConfigs
import com.sanjeevyadavit.magecart.service.ApiInterface
import com.sanjeevyadavit.magecart.service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivityViewModel: ViewModel() {
    private val facade: ApiInterface = RetrofitClient.getInstance().create(ApiInterface::class.java)

    private var _storeConfigs: StoreConfigs? = null
    val storeConfigs: StoreConfigs?
        get() = _storeConfigs

    init {
        getStoreConfig()
    }

    private fun getStoreConfig(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // TODO: this logic should be in Repository
                val response = facade.storeConfigs()
                withContext(Dispatchers.Main) {
                    _storeConfigs = response[0]
                }
            } catch (e: Exception) {
                e.message?.let { Log.e("MYTAG", it) }
            }
        }
    }
}