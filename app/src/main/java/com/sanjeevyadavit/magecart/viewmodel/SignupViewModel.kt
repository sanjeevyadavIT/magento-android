package com.sanjeevyadavit.magecart.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.model.Customer
import com.sanjeevyadavit.magecart.model.LoginBodyRequest
import com.sanjeevyadavit.magecart.model.SignupBodyRequest
import com.sanjeevyadavit.magecart.model.SignupResponse
import com.sanjeevyadavit.magecart.service.ApiInterface
import com.sanjeevyadavit.magecart.service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class SignupViewModel(app: Application): AndroidViewModel(app) {
    val email: MutableLiveData<String> = MutableLiveData("")
    val password: MutableLiveData<String> = MutableLiveData("")
    val firstName: MutableLiveData<String> = MutableLiveData("")
    val lastName: MutableLiveData<String> = MutableLiveData("")

    fun signup(){
        if(handleEmptyValues()) return
        makeSignupApiCall()
    }

    private fun handleEmptyValues(): Boolean{
        if(email.value!!.isEmpty()  || password.value!!.isEmpty() || firstName.value!!.isEmpty() || lastName.value!!.isEmpty()) {
            showToast("Fields cannot be empty")
            return true;
        }
        return false
    }

    private fun makeSignupApiCall(){
        viewModelScope.launch(Dispatchers.IO) {
            val facade: ApiInterface = RetrofitClient.getInstance().create(ApiInterface::class.java)
            facade.signup(SignupBodyRequest(Customer(email.value!!, firstname = firstName.value!!, lastname = lastName.value!!), password.value!!)).enqueue(
                object: retrofit2.Callback<SignupResponse> {
                    override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                        if (response.code() == 200) {
                            response.body()?.let { Log.d("SANJEEV", it.toString()) }
                            showToast("Signup Successfully!!!")
                            resetValues()
                        } else {
                            showToast("Something went wrong")
                        }
                    }

                    override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                        showToast("Something went wrong")
                    }

                }
            )
        }
    }

    private fun resetValues () {
        email.value  = ""
        password.value = ""
        firstName.value = ""
        lastName.value = ""
    }

    private fun showToast(msg: String) =  Toast.makeText(getApplication(), msg, Toast.LENGTH_SHORT).show()
}