package com.sanjeevyadavit.magecart.domain.repository

import com.sanjeevyadavit.magecart.data.remote.dto.*
import com.sanjeevyadavit.magecart.data.remote.dto.product.ProductsDto
import com.sanjeevyadavit.magecart.data.remote.dto.Filter

interface MageCartRepository {

    suspend fun getStoreConfigs(): List<StoreConfigsDto>

    suspend fun login(body: LoginBodyRequest): String

    suspend fun signup(body: SignupBodyRequest): SignupDto

    suspend fun getCategories(): CategoriesDto

    suspend fun getProducts(
        filters: List<Filter>,
        pageSize: Int = 20,
        currentPage: Int = 1
    ): ProductsDto
}