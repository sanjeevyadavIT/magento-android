package com.sanjeevyadavit.magecart.presentation.categories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.common.model.IState
import com.sanjeevyadavit.magecart.common.model.Resource
import com.sanjeevyadavit.magecart.domain.model.Category
import com.sanjeevyadavit.magecart.domain.use_case.GetCategoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(
    private val getCategoryListUseCase: GetCategoryListUseCase
): ViewModel() {

    private val _state = mutableStateOf(IState<List<Category>>())
    val state: State<IState<List<Category>>>
        get() = _state

    init {
        getCategoryList()
    }

    private fun getCategoryList(){
        getCategoryListUseCase().onEach { result ->
            _state.value = when(result) {
                is Resource.Loading -> IState(isLoading = true)
                is Resource.Error -> IState(error = result.message ?: "An unexpected error occurred")
                is Resource.Success ->  IState(data = result.data)
            }
        }.launchIn(viewModelScope)
    }
}