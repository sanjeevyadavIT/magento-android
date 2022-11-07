package com.sanjeevyadavit.magecart.service

import com.sanjeevyadavit.magecart.model.CategoryTree
import com.sanjeevyadavit.magecart.model.LoginBodyRequest
import com.sanjeevyadavit.magecart.model.SignupBodyRequest
import com.sanjeevyadavit.magecart.model.SignupResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @POST("V1/integration/customer/token")
    suspend fun login(@Body loginBodyRequest: LoginBodyRequest): String

    @POST("V1/customers")
    suspend fun signup(@Body signupBodyRequest: SignupBodyRequest): SignupResponse

    @GET("V1/categories")
    suspend fun getCategoryTree(): CategoryTree

}