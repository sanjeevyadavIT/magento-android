package com.sanjeevyadavit.magecart.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.model.LoginBodyRequest
import com.sanjeevyadavit.magecart.service.ApiInterface
import com.sanjeevyadavit.magecart.service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel(app: Application): AndroidViewModel(app) {

    private val facade: ApiInterface = RetrofitClient.getInstance().create(ApiInterface::class.java)

    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")

    private val _customerToken = MutableLiveData<String?>(null)
    val customerToken: LiveData<String?>
        get() = _customerToken

    fun login(){
        if(handleEmptyValues()) return
        makeLoginApiCall()
    }

    private fun handleEmptyValues(): Boolean{
        if(email.value.isNullOrEmpty()  || password.value.isNullOrEmpty()) {
           showToast("Fields cannot be empty")
            return true;
        }
        return false
    }

    private fun makeLoginApiCall(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                // TODO: this logic should be in Repository
                val response = facade.login(LoginBodyRequest(email.value!!, password.value!!))
                withContext(Dispatchers.Main) {
                    _customerToken.value = response
                    showToast("Logged In Successfully!!!")
                    resetValues()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showToast("Error: ${e.message}")
                }
            }
        }
    }

    private fun resetValues () {
        email.value  = ""
        password.value = ""
    }

    private fun showToast(msg: String) =  Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show()
}