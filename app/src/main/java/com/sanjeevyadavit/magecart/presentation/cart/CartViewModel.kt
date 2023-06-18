package com.sanjeevyadavit.magecart.presentation.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.common.model.Resource
import com.sanjeevyadavit.magecart.domain.model.Cart
import com.sanjeevyadavit.magecart.domain.use_case.GetCustomerCartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val getCustomerCartUseCase: GetCustomerCartUseCase
): ViewModel() {

    private val _cart = mutableStateOf<Cart?>(null)
    val cart: State<Cart?>
        get() = _cart

    fun fetchCustomerCart(customerSessionToken: String, firstTimeLoggedIn: Boolean = false) {
        getCustomerCartUseCase(customerSessionToken, firstTimeLoggedIn).onEach {
            when (it) {
                is Resource.Loading -> {
                }
                is Resource.Error -> {
                    // TODO: DO something
                }
                is Resource.Success -> {
                    _cart.value = it.data
                }
            }
        }.launchIn(viewModelScope)
    }
}