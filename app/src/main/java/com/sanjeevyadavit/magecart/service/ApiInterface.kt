package com.sanjeevyadavit.magecart.service

import com.sanjeevyadavit.magecart.model.LoginBodyRequest
import com.sanjeevyadavit.magecart.model.SignupBodyRequest
import com.sanjeevyadavit.magecart.model.SignupResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {

    @POST("V1/integration/customer/token")
    fun login(@Body loginBodyRequest: LoginBodyRequest): Call<String>

    @POST("V1/customers")
    fun signup(@Body signupBodyRequest: SignupBodyRequest): Call<SignupResponse>
}