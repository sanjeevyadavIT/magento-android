package com.sanjeevyadavit.magecart.presentation

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.common.Resource
import com.sanjeevyadavit.magecart.domain.model.StoreConfigs
import com.sanjeevyadavit.magecart.domain.use_case.GetStoreConfigsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val storeConfigsUseCase: GetStoreConfigsUseCase
) : ViewModel() {

    private val _storeConfigs = mutableStateOf<StoreConfigs?>(null)
    val storeConfigs: State<StoreConfigs?>
        get() = _storeConfigs

    init {
        getStoreConfig()
    }

    private fun getStoreConfig() {
        storeConfigsUseCase().onEach {
            if(it is Resource.Success){
                _storeConfigs.value = it.data
            }
        }.launchIn(viewModelScope)
    }
}