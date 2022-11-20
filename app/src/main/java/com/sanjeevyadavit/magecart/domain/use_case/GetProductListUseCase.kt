package com.sanjeevyadavit.magecart.domain.use_case

import com.sanjeevyadavit.magecart.common.Resource
import com.sanjeevyadavit.magecart.data.remote.dto.Filter
import com.sanjeevyadavit.magecart.data.remote.dto.product.toProduct
import com.sanjeevyadavit.magecart.data.remote.dto.toCategory
import com.sanjeevyadavit.magecart.domain.model.Category
import com.sanjeevyadavit.magecart.domain.model.Product
import com.sanjeevyadavit.magecart.domain.model.ProductListData
import com.sanjeevyadavit.magecart.domain.repository.MageCartRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetProductListUseCase @Inject constructor(private val repository: MageCartRepository) {
    operator fun invoke(filters: List<Filter>, pageSize: Int, currentPage: Int) = flow {
        try {
            emit(Resource.Loading<ProductListData>())
            val products = repository.getProducts(filters, pageSize, currentPage)

            emit(Resource.Success<ProductListData>(ProductListData(products = products.items.map { it.toProduct() }, products.totalCount)))
        } catch (e: HttpException) {
            emit(Resource.Error<ProductListData>(e.localizedMessage ?: "API failure"))
        } catch(e: Exception) {
            emit(Resource.Error<ProductListData>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}