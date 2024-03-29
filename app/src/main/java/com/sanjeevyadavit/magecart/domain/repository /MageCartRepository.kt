package com.sanjeevyadavit.magecart.domain.repository

import com.sanjeevyadavit.magecart.data.remote.dto.*
import com.sanjeevyadavit.magecart.data.remote.dto.product.ProductsDto
import com.sanjeevyadavit.magecart.data.remote.dto.Filter
import com.sanjeevyadavit.magecart.data.remote.dto.attribute.AttributeDataDto
import com.sanjeevyadavit.magecart.data.remote.dto.carts.AddItemToCartBodyRequest
import com.sanjeevyadavit.magecart.data.remote.dto.carts.CartDto
import com.sanjeevyadavit.magecart.data.remote.dto.carts.CartItemDto
import com.sanjeevyadavit.magecart.data.remote.dto.product.ProductDto
import com.sanjeevyadavit.magecart.data.remote.dto.profile.UserDto

interface MageCartRepository {

    suspend fun getStoreConfigs(): List<StoreConfigsDto>

    suspend fun getCmsBlock(blockId: Int): CmsBlockDto

    suspend fun login(body: LoginBodyRequest): String

    suspend fun signup(body: SignupBodyRequest): SignupDto

    suspend fun getCategories(): CategoriesDto

    suspend fun getProducts(
        filters: List<Filter>,
        pageSize: Int = 20,
        currentPage: Int = 1
    ): ProductsDto

    suspend fun getProductDetail(
        sku: String
    ): ProductDto

    suspend fun getAttributeData(attributeId: Int): AttributeDataDto

    suspend fun getCustomerCart(authorizationToken: String): CartDto

    suspend fun createQuoteId(authorizationToken: String): Int

    suspend fun addItemToCart(authorizationToken: String, itemToCartBodyRequest: AddItemToCartBodyRequest): CartItemDto

    suspend fun fetchLoggedInCustomerDetails(authorizationToken: String): UserDto
}