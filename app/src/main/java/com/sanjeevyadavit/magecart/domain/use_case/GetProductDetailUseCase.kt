package com.sanjeevyadavit.magecart.domain.use_case

import com.sanjeevyadavit.magecart.common.model.Resource
import com.sanjeevyadavit.magecart.data.remote.dto.product.toProduct
import com.sanjeevyadavit.magecart.domain.model.Product
import com.sanjeevyadavit.magecart.domain.repository.MageCartRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(private val repository: MageCartRepository) {
    operator fun invoke(sku: String) = flow {
        try {
            emit(Resource.Loading<Product>())
            val product = repository.getProductDetail(sku).toProduct()
            emit(Resource.Success(product))
        } catch (e: HttpException) {
            emit(Resource.Error<Product>(e.localizedMessage ?: "API failure"))
        } catch(e: Exception) {
            emit(Resource.Error<Product>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}