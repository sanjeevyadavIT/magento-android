package com.sanjeevyadavit.magecart.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.common.model.Resource
import com.sanjeevyadavit.magecart.domain.model.AttributeData
import com.sanjeevyadavit.magecart.domain.model.Cart
import com.sanjeevyadavit.magecart.domain.model.StoreConfigs
import com.sanjeevyadavit.magecart.domain.use_case.GetAttributeDataUseCase
import com.sanjeevyadavit.magecart.domain.use_case.GetCustomerCartUseCase
import com.sanjeevyadavit.magecart.domain.use_case.GetStoreConfigsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val storeConfigsUseCase: GetStoreConfigsUseCase,
    private val getAttributeDataUseCase: GetAttributeDataUseCase,
) : ViewModel() {

    private val _storeConfigs = mutableStateOf<StoreConfigs?>(null)
    val storeConfigs: State<StoreConfigs?>
        get() = _storeConfigs

    private val _attributeMap = mutableStateOf<HashMap<Int, AttributeData>>(hashMapOf())
    val attributeMap: State<HashMap<Int, AttributeData>>
        get() = _attributeMap

    init {
        getStoreConfig()
    }

    private fun getStoreConfig() {
        storeConfigsUseCase().onEach {
            if (it is Resource.Success) {
                _storeConfigs.value = it.data
            }
        }.launchIn(viewModelScope)
    }

    // QUESTION: What is better way to handle this?
    fun fetchAttributeData(attributeId: Int) {
        getAttributeDataUseCase(attributeId).onEach {
            if (it is Resource.Success) {
                _attributeMap.value = _attributeMap.value.toMutableMap().apply {
                    it.data?.let { data -> put(attributeId, data) }
                } as HashMap<Int, AttributeData>
            }
        }.launchIn(viewModelScope)
    }
}