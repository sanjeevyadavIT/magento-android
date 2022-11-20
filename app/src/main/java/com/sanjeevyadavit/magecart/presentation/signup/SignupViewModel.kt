package com.sanjeevyadavit.magecart.presentation.signup

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.common.IState
import com.sanjeevyadavit.magecart.common.Resource
import com.sanjeevyadavit.magecart.domain.model.SignupResponse
import com.sanjeevyadavit.magecart.domain.use_case.SignupUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase,
    app: Application
) : AndroidViewModel(app) {

    private val _state: MutableLiveData<IState<SignupResponse>> = MutableLiveData(IState())

    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")
    val firstName: MutableLiveData<String> = MutableLiveData("")
    val lastName: MutableLiveData<String> = MutableLiveData("")

    fun signup() {
        if (handleEmptyValues()) return
        makeSignupApiCall()
    }

    private fun handleEmptyValues(): Boolean {
        if (email.value.isNullOrEmpty() || password.value.isNullOrEmpty() || firstName.value.isNullOrEmpty() || lastName.value.isNullOrEmpty()) {
            showToast("Fields cannot be empty")
            return true;
        }
        return false
    }

    private fun makeSignupApiCall() {
        signupUseCase(firstName.value!!, lastName.value!!, email.value!!, password.value!!).onEach { result ->
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
                    withContext(Dispatchers.Main){
                        showToast("Signup Successfully!!!")
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
        firstName.value = ""
        lastName.value = ""
    }

    // TODO: Remove toast message from View Model
    private fun showToast(msg: String) =
        Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show()
}