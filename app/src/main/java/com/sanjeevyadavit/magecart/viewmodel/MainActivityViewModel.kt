package com.sanjeevyadavit.magecart.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanjeevyadavit.magecart.LoginBodyRequest
import com.sanjeevyadavit.magecart.service.ApiInterface
import com.sanjeevyadavit.magecart.service.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MainActivityViewModel: ViewModel() {
    val isLoggedIn: MutableLiveData<Boolean> = MutableLiveData(false)

    fun login(){
        viewModelScope.launch(Dispatchers.IO) {
            val facade: ApiInterface = RetrofitClient.getInstance().create(ApiInterface::class.java)
            val response = facade.login(LoginBodyRequest("test001@gmail.com", "password123")).enqueue(
                object: retrofit2.Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.code() == 200) {
                            response.body()?.let { Log.d("SANJEEV", it) }
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                }
            )
        }
        isLoggedIn.value = true
    }
}