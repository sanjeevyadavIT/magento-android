package com.sanjeevyadavit.magecart.presentation.productDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.common.model.IState
import com.sanjeevyadavit.magecart.common.model.Resource
import com.sanjeevyadavit.magecart.domain.model.ProductDetail
import com.sanjeevyadavit.magecart.domain.use_case.AddItemToCartUseCase
import com.sanjeevyadavit.magecart.domain.use_case.GetProductDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getProductDetailUseCase: GetProductDetailUseCase,
    private val addItemToCartUseCase: AddItemToCartUseCase
): ViewModel() {
    private val productSku = savedStateHandle.get<String>("productSku")

    private val _state = mutableStateOf(IState<ProductDetail>())
    val state: State<IState<ProductDetail>>
        get() = _state

    private val _addToCartStatus = mutableStateOf(IState<Boolean>())
    val addToCartStatus: State<IState<Boolean>>
        get() = _addToCartStatus

    init {
        getProductDetail()
    }

    private fun getProductDetail() {
        getProductDetailUseCase(productSku ?: "").onEach { result ->
            _state.value = when(result) {
                is Resource.Loading -> IState(isLoading = true)
                is Resource.Error -> IState(error = result.message ?: "An unexpected error occurred")
                is Resource.Success -> IState(data = result.data)
            }
        }.launchIn(viewModelScope)
    }

    fun addItemToCart(customerSessionToken: String, sku: String, quantity: Int = 1, cartId: Int){
        addItemToCartUseCase.invoke(
            customerSessionToken = customerSessionToken,
            sku = sku,
            quantity = quantity,
            cartId = cartId
        ).onEach { result ->
            _addToCartStatus.value = when(result){
                is Resource.Loading -> IState(isLoading = true)
                is Resource.Error -> IState(error = result?.message ?: "Unable to add item to cart, something went wrong")
                is Resource.Success -> IState(data = true)
            }
        }.launchIn(viewModelScope)
    }
}