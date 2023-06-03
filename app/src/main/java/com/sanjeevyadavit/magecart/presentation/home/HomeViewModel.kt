package com.sanjeevyadavit.magecart.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.common.model.IState
import com.sanjeevyadavit.magecart.common.model.Resource
import com.sanjeevyadavit.magecart.data.remote.dto.Filter
import com.sanjeevyadavit.magecart.domain.model.HomeScreenSkeletonData
import com.sanjeevyadavit.magecart.domain.model.Product
import com.sanjeevyadavit.magecart.domain.use_case.GetHomeScreenSkeletonDataUseCase
import com.sanjeevyadavit.magecart.domain.use_case.GetProductListUseCase
import com.sanjeevyadavit.magecart.presentation.products.PAGE_SIZE
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeScreenSkeletonDataUseCase: GetHomeScreenSkeletonDataUseCase,
    private val getProductListUseCase: GetProductListUseCase
): ViewModel() {

    private val _state = mutableStateOf(IState<HomeScreenSkeletonData>())
    val state: State<IState<HomeScreenSkeletonData>>
        get() = _state

    private val _featuredCategoryState = mutableStateOf(HashMap<Int, IState<List<Product>>>())
    val featuredCategoryState: State<HashMap<Int, IState<List<Product>>>>
        get() = _featuredCategoryState

    init {
        getHomeScreenSkeletonData()
    }

    private fun getHomeScreenSkeletonData(){
        getHomeScreenSkeletonDataUseCase().onEach { result ->
            _state.value = when(result) {
                is Resource.Loading -> IState(isLoading = true)
                is Resource.Error -> IState(error = result.message ?: "An unexpected error occurred")
                is Resource.Success -> {
                    fetchFeaturedProductList(result.data)
                    IState(data = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    // QUESTION: @Gobind is this right way of doing it? after I have fetch home data, home data contain ids, for each id, product list need to be fetch from different api and shown in UI
    private fun fetchFeaturedProductList(skeletonData: HomeScreenSkeletonData?) {
        skeletonData?.featuredCategories?.forEach {
            _featuredCategoryState.value = _featuredCategoryState.value.toMutableMap().apply {
                put(it.categoryId, IState(isLoading = true))
            } as HashMap<Int, IState<List<Product>>>
            val filters = mutableListOf<Filter>(Filter(field = "category_id", value = it.categoryId))
            getProductListUseCase(
                filters = filters,
                currentPage = 1,
                pageSize = PAGE_SIZE
            ).onEach { result ->
                when(result) {
                    is Resource.Error -> {
                        _featuredCategoryState.value = _featuredCategoryState.value.toMutableMap().apply {
                            put(it.categoryId, IState(error = result.message ?: "An unexpected error occurred"))
                        } as HashMap<Int, IState<List<Product>>>
                    }
                    is Resource.Success -> {
                        // QUESTION: @Gobind is this right way of updating state or is there any better way to structure state
                        _featuredCategoryState.value = _featuredCategoryState.value.toMutableMap().apply {
                            put(it.categoryId, IState(result.data?.products))
                        } as HashMap<Int, IState<List<Product>>>
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}