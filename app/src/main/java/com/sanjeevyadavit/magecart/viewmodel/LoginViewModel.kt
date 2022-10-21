package com.sanjeevyadavit.magecart.viewmodel

import android.app.Application
import android.util.Log
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
import retrofit2.Call
import retrofit2.Response

class LoginViewModel(app: Application): AndroidViewModel(app) {
    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")

    private val _customerToken = MutableLiveData<String?>(null)
    val customerToken: MutableLiveData<String?>
        get() = _customerToken

    fun login(){
        if(handleEmptyValues()) return
        makeLoginApiCall()
    }

    private fun handleEmptyValues(): Boolean{
        if(email.value!!.isEmpty()  || password.value!!.isEmpty()) {
           showToast("Fields cannot be empty")
            return true;
        }
        return false
    }

    private fun makeLoginApiCall(){
        viewModelScope.launch(Dispatchers.IO) {
            val facade: ApiInterface = RetrofitClient.getInstance().create(ApiInterface::class.java)
            facade.login(LoginBodyRequest(email.value!!, password.value!!)).enqueue(
                object: retrofit2.Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.code() == 200) {
                            response.body()?.let { _customerToken.value = it }
                            showToast("Logged In Successfully!!!")
                            resetValues()
                        } else {
                            showToast("Something went wrong")
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        showToast("Something went wrong")
                    }

                }
            )
        }
    }

    private fun resetValues () {
        email.value  = ""
        password.value = ""
    }

    private fun showToast(msg: String) =  Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show()
}