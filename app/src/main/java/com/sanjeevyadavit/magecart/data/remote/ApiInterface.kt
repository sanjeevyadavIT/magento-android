package com.sanjeevyadavit.magecart.data.remote

import com.sanjeevyadavit.magecart.common.Constants
import com.sanjeevyadavit.magecart.data.remote.dto.*
import com.sanjeevyadavit.magecart.data.remote.dto.product.ProductDto
import com.sanjeevyadavit.magecart.data.remote.dto.product.ProductsDto
import retrofit2.http.*

interface ApiInterface {

    @GET("V1/store/storeConfigs")
    suspend fun storeConfigs(): List<StoreConfigsDto>

    @GET("V1/cmsBlock/{blockId}")
    suspend fun getCmsBlock(@Path("blockId") blockId: Int): CmsBlockDto

    @POST("V1/integration/customer/token")
    suspend fun login(@Body body: LoginBodyRequest): String

    @POST("V1/customers")
    suspend fun signup(@Body body: SignupBodyRequest): SignupDto

    @GET("V1/categories")
    suspend fun getCategoryTree(): CategoriesDto

    @GET("V1/products")
    suspend fun getProducts(
        // QUESTION: Is there any better way to handle this, where you can send multiple filters
        @Query("searchCriteria[filterGroups][0][filters][0][field]") filter0Field: String,
        @Query("searchCriteria[filterGroups][0][filters][0][value]") filter0Value: Int,
        @Query("searchCriteria[filterGroups][0][filters][0][conditionType]") filter0ConditionType: String = "eq",
        @Query("searchCriteria[filterGroups][1][filters][0][field]") filter1Field: String = "visibility",
        @Query("searchCriteria[filterGroups][1][filters][0][value]") filter1Value: Int = 4,
        @Query("searchCriteria[filterGroups][1][filters][0][conditionType]") filter1ConditionType: String = "eq",
        @Query("searchCriteria[pageSize]") pageSize: Int = 20,
        @Query("searchCriteria[currentPage]") currentPage: Int = 1
    ): ProductsDto

    @GET("V1/products/{sku}")
    suspend fun getProductDetail(@Path("sku") sku: String): ProductDto

    companion object {
        const val API_BASE_URL = "${Constants.BASE_URL}rest/default/"
    }
}