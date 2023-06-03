package com.sanjeevyadavit.magecart.data.repository

import com.sanjeevyadavit.magecart.data.remote.ApiInterface
import com.sanjeevyadavit.magecart.data.remote.dto.*
import com.sanjeevyadavit.magecart.data.remote.dto.attribute.AttributeDataDto
import com.sanjeevyadavit.magecart.data.remote.dto.carts.AddItemToCartBodyRequest
import com.sanjeevyadavit.magecart.data.remote.dto.carts.CartDto
import com.sanjeevyadavit.magecart.data.remote.dto.carts.CartItemDto
import com.sanjeevyadavit.magecart.data.remote.dto.product.ProductDto
import com.sanjeevyadavit.magecart.data.remote.dto.product.ProductsDto
import com.sanjeevyadavit.magecart.data.remote.dto.profile.UserDto
import com.sanjeevyadavit.magecart.domain.repository.MageCartRepository
import javax.inject.Inject

class MageCartRepositoryImpl @Inject constructor(private val api: ApiInterface) :
    MageCartRepository {
    override suspend fun getStoreConfigs(): List<StoreConfigsDto> = api.storeConfigs()

    override suspend fun getCmsBlock(blockId: Int): CmsBlockDto = api.getCmsBlock(blockId)

    override suspend fun login(body: LoginBodyRequest): String = api.login(body)

    override suspend fun signup(body: SignupBodyRequest): SignupDto = api.signup(body)

    override suspend fun getCategories(): CategoriesDto = api.getCategoryTree()

    override suspend fun getProducts(
        filters: List<Filter>,
        pageSize: Int,
        currentPage: Int
    ): ProductsDto = api.getProducts(
        filter0Field = filters.getOrNull(0)?.field ?: "",
        filter0Value = filters.getOrNull(0)?.value ?: 0,
        filter0ConditionType = filters.getOrNull(0)?.conditionType ?: "eq",
        filter1Field = filters.getOrNull(1)?.field ?: "visibility",
        filter1Value = filters.getOrNull(1)?.value ?: 4,
        filter1ConditionType = filters.getOrNull(1)?.conditionType ?: "eq",
        pageSize = pageSize,
        currentPage = currentPage
    )

    override suspend fun getProductDetail(sku: String): ProductDto = api.getProductDetail(sku)

    override suspend fun getAttributeData(attributeId: Int): AttributeDataDto = api.getAttributeData(attributeId)

    override suspend fun getCustomerCart(authorizationToken: String): CartDto = api.getCustomerCart(authorizationToken)

    override suspend fun createQuoteId(authorizationToken: String): Int = api.createQuoteId(authorizationToken)

    override suspend fun addItemToCart(authorizationToken: String, itemToCartBodyRequest: AddItemToCartBodyRequest): CartItemDto = api.addItemToCart(authorizationToken, itemToCartBodyRequest)

    override suspend fun fetchLoggedInCustomerDetails(authorizationToken: String): UserDto = api.fetchLoggedInCustomerDetails(authorizationToken)
}