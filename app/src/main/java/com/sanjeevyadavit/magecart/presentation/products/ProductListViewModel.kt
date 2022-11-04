package com.sanjeevyadavit.magecart.presentation.products

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.common.IState
import com.sanjeevyadavit.magecart.common.Resource
import com.sanjeevyadavit.magecart.data.remote.dto.Filter
import com.sanjeevyadavit.magecart.domain.model.Product
import com.sanjeevyadavit.magecart.domain.use_case.GetProductListUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

const val PAGE_SIZE = 10

// QUESTION: How to have this as @HiltViewModel, when categoryId need to be provided on runtime and getProductListUseCase on build time ?
class ProductListViewModel constructor(
    categoryId: Int,
    var getProductListUseCase: GetProductListUseCase
) : ViewModel() {

    private val _state = mutableStateOf(IState<List<Product>>())
    val state = _state

    private var _loadMore = mutableStateOf(false)
    val loadMore = _loadMore

    private var totalCount: Int = 0
    private var productListScrollPosition = 0

    private val filters = mutableListOf<Filter>(Filter(field = "category_id", value = categoryId))

    init {
        getProducts()
    }

    private fun getProducts() {
        // QUESTION: Where should this pagination logic resides?
        val currentPage = ((_state.value.data?.size ?: 0) / PAGE_SIZE) + 1;
        if (currentPage > 1) {
            _loadMore.value = true
        }
        getProductListUseCase(
            filters = filters,
            currentPage = currentPage,
            pageSize = PAGE_SIZE
        ).onEach { result ->
            _state.value = when (result) {
                is Resource.Loading -> if (currentPage > 1) _state.value else IState(isLoading = true)
                is Resource.Error -> IState(
                    error = result.message ?: "An unexpected error occurred"
                )
                is Resource.Success -> {
                    totalCount = result.data?.totalCount ?: 0
                    IState(data = result.data?.products)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun fetchNextPage() {
        // prevent duplicate event due to recompose happening to quickly
        if ((productListScrollPosition + 1) < (totalCount) && !_loadMore.value) {
            getProducts()
        }
    }

    fun onChangeProductListScrollPosition(position: Int) {
        productListScrollPosition = position
    }

    fun getTotalCount(): Int = totalCount

}