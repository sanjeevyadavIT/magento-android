package com.sanjeevyadavit.magecart.domain.use_case

import com.sanjeevyadavit.magecart.common.model.Resource
import com.sanjeevyadavit.magecart.data.remote.dto.product.toProductDetail
import com.sanjeevyadavit.magecart.domain.model.ProductDetail
import com.sanjeevyadavit.magecart.domain.repository.MageCartRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(private val repository: MageCartRepository) {
    operator fun invoke(sku: String) = flow {
        try {
            emit(Resource.Loading<ProductDetail>())
            val product = repository.getProductDetail(sku).toProductDetail()
            emit(Resource.Success(product))
        } catch (e: HttpException) {
            emit(Resource.Error<ProductDetail>(e.localizedMessage ?: "API failure"))
        } catch(e: Exception) {
            emit(Resource.Error<ProductDetail>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}