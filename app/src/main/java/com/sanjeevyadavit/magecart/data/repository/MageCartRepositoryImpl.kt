package com.sanjeevyadavit.magecart.data.repository

import com.sanjeevyadavit.magecart.data.remote.ApiInterface
import com.sanjeevyadavit.magecart.data.remote.dto.*
import com.sanjeevyadavit.magecart.data.remote.dto.product.ProductsDto
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
}