package com.sanjeevyadavit.magecart.presentation.login

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.sanjeevyadavit.magecart.common.IState
import com.sanjeevyadavit.magecart.common.Resource
import com.sanjeevyadavit.magecart.domain.use_case.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    app: Application
) : AndroidViewModel(app) {

    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")

    private val _state = MutableLiveData<IState<String>>(null)
    val state = _state

    fun login() {
        if (handleEmptyValues()) return
        makeLoginApiCall()
    }

    private fun handleEmptyValues(): Boolean {
        // QUESTION: Where should validation logic reside in ViewModel or useCase ?
        if (email.value.isNullOrEmpty() || password.value.isNullOrEmpty()) {
            // TODO: Provide error state in UI
            showToast("Fields cannot be empty")
            return true;
        }
        return false
    }

    private fun makeLoginApiCall() {
        loginUseCase(email.value!!, password.value!!).onEach { result ->
            _state.value = when (result) {
                is Resource.Loading -> IState(isLoading = true)
                is Resource.Error -> {
                   val message = result.message ?: "An unexpected error occurred"
                    showToast(message)
                    IState(
                        error = message
                    )
                }
                is Resource.Success -> {
                    withContext(Dispatchers.Main) {
                        resetValues()
                    }
                    IState(data = result.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun resetValues() {
        email.value = ""
        password.value = ""
    }

    private fun showToast(msg: String) =
        Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show()
}