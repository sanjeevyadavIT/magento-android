package com.sanjeevyadavit.magecart.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.common.model.IState
import com.sanjeevyadavit.magecart.common.model.Resource
import com.sanjeevyadavit.magecart.domain.model.User
import com.sanjeevyadavit.magecart.domain.use_case.GetLoggedInUserDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getLoggedInUserDetailsUseCase: GetLoggedInUserDetailsUseCase
): ViewModel() {
    private val _state = mutableStateOf(IState<User>())
    val state: State<IState<User>>
        get() = _state

    fun getLoggedInUserDetails(customerToken: String){
        getLoggedInUserDetailsUseCase.invoke(customerToken).onEach { result ->
            _state.value = when(result) {
                is Resource.Loading -> IState(isLoading = true)
                is Resource.Error -> IState(error = result?.message ?: "Unable to add item to cart, something went wrong")
                is Resource.Success -> IState(data = result.data)
            }
        }.launchIn(viewModelScope)
    }
}