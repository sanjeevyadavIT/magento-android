package com.sanjeevyadavit.magecart.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.common.IState
import com.sanjeevyadavit.magecart.common.Resource
import com.sanjeevyadavit.magecart.domain.model.HomeScreenSkeletonData
import com.sanjeevyadavit.magecart.domain.use_case.GetHomeScreenSkeletonDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getHomeScreenSkeletonDataUseCase: GetHomeScreenSkeletonDataUseCase): ViewModel() {

    private val _state = mutableStateOf(IState<HomeScreenSkeletonData>())
    val state: State<IState<HomeScreenSkeletonData>>
        get() = _state

    init {
        getHomeScreenSkeletonData()
    }

    private fun getHomeScreenSkeletonData(){
        getHomeScreenSkeletonDataUseCase().onEach { result ->
            _state.value = when(result) {
                is Resource.Loading -> IState(isLoading = true)
                is Resource.Error -> IState(error = result.message ?: "An unexpected error occurred")
                is Resource.Success ->  IState(data = result.data)
            }
        }.launchIn(viewModelScope)
    }
}