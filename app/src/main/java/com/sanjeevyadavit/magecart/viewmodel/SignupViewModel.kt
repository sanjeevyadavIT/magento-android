package com.sanjeevyadavit.magecart.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.model.Customer
import com.sanjeevyadavit.magecart.model.SignupBodyRequest
import com.sanjeevyadavit.magecart.service.ApiInterface
import com.sanjeevyadavit.magecart.service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignupViewModel(app: Application) : AndroidViewModel(app) {
    private val facade: ApiInterface = RetrofitClient.getInstance().create(ApiInterface::class.java)

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
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // TODO: this logic should be in Repository
                val response = facade.signup(
                    SignupBodyRequest(
                        Customer(
                            email.value!!,
                            firstname = firstName.value!!,
                            lastname = lastName.value!!
                        ), password.value!!
                    )
                )
                Log.d("SANJEEV", response.toString())
                withContext(Dispatchers.Main){
                    showToast("Signup Successfully!!!")
                    resetValues()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showToast("Error: ${e.message}")
                }
            }
        }
    }

    private fun resetValues() {
        email.value = ""
        password.value = ""
        firstName.value = ""
        lastName.value = ""
    }

    private fun showToast(msg: String) =
        Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show()
}