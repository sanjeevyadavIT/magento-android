package com.sanjeevyadavit.magecart.service

import com.sanjeevyadavit.magecart.model.*
import com.sanjeevyadavit.magecart.model.product.Products
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {

    @GET("V1/store/storeConfigs")
    suspend fun storeConfigs(): List<StoreConfigs>

    @POST("V1/integration/customer/token")
    suspend fun login(@Body loginBodyRequest: LoginBodyRequest): String

    @POST("V1/customers")
    suspend fun signup(@Body signupBodyRequest: SignupBodyRequest): SignupResponse

    @GET("V1/categories")
    suspend fun getCategoryTree(): CategoryTree

    @GET("V1/products")
    suspend fun getProducts(
        @Query("searchCriteria[filterGroups][0][filters][0][field]") filter0Field: String,
        @Query("searchCriteria[filterGroups][0][filters][0][value]") filter0Value: Int,
        @Query("searchCriteria[filterGroups][0][filters][0][conditionType]") filter0ConditionType: String = "eq",
        @Query("searchCriteria[filterGroups][1][filters][0][field]") filter1Field: String = "visibility",
        @Query("searchCriteria[filterGroups][1][filters][0][value]") filter1Value: Int = 4,
        @Query("searchCriteria[filterGroups][1][filters][0][conditionType]") filter1ConditionType: String = "eq",
        @Query("searchCriteria[pageSize]") pageSize: Int = 20,
        @Query("searchCriteria[currentPage]") currentPage: Int = 1
    ): Products

}