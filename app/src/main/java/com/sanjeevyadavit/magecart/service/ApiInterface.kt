package com.sanjeevyadavit.magecart.service

import com.sanjeevyadavit.magecart.LoginBodyRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("V1/integration/customer/token")
    fun login(@Body loginBodyRequest: LoginBodyRequest): Call<String>
}