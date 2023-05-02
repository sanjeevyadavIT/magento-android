package com.sanjeevyadavit.magecart.presentation.products

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.common.model.IState
import com.sanjeevyadavit.magecart.common.model.Resource
import com.sanjeevyadavit.magecart.data.remote.dto.Filter
import com.sanjeevyadavit.magecart.domain.model.Product
import com.sanjeevyadavit.magecart.domain.use_case.GetProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.properties.Delegates

const val PAGE_SIZE = 10

@HiltViewModel
class ProductListViewModel @Inject constructor(
    var getProductListUseCase: GetProductListUseCase
) : ViewModel() {

    private var categoryId by Delegates.notNull<Int>()

    private val _state = mutableStateOf(IState<List<Product>>())
    val state: State<IState<List<Product>>>
        get() = _state

    private var _loadMore = mutableStateOf(false)
    val loadMore: State<Boolean>
        get() = _loadMore

    private var totalCount: Int = 0
    private var productListScrollPosition = 0

    fun getProducts(categoryId: Int) {
        this.categoryId = categoryId
        val filters = mutableListOf<Filter>(Filter(field = "category_id", value = categoryId))
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
                is Resource.Error -> {
                    _loadMore.value = false
                    IState(error = result.message ?: "An unexpected error occurred")
                }
                is Resource.Success -> {
                    totalCount = result.data?.totalCount ?: 0
                    val products = ArrayList(_state.value.data ?: listOf())
                    products.addAll(result.data?.products ?: listOf())
                    _loadMore.value = false
                    IState(data = products)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun fetchNextPage() {
        // prevent duplicate event due to recompose happening to quickly
        if ((productListScrollPosition + 1) < (totalCount) && !_loadMore.value) {
            getProducts(categoryId)
        }
    }

    fun onChangeProductListScrollPosition(position: Int) {
        productListScrollPosition = position
    }

    fun getTotalCount(): Int = totalCount

}