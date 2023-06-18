package com.sanjeevyadavit.magecart.domain.use_case

import com.sanjeevyadavit.magecart.common.model.Resource
import com.sanjeevyadavit.magecart.data.remote.dto.carts.AddItemToCartBodyRequest
import com.sanjeevyadavit.magecart.data.remote.dto.carts.CartItem
import com.sanjeevyadavit.magecart.data.remote.dto.carts.toAddToCartResponse
import com.sanjeevyadavit.magecart.domain.model.AddToCartResponse
import com.sanjeevyadavit.magecart.domain.repository.MageCartRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class AddItemToCartUseCase @Inject constructor(private val repository: MageCartRepository) {

    operator fun invoke(customerSessionToken: String, sku: String, quantity: Int = 1, cartId: Int) = flow {
        try {
            val token = "Bearer $customerSessionToken"
            emit(Resource.Loading<AddToCartResponse>())
            val data = repository.addItemToCart(
                authorizationToken = token,
                itemToCartBodyRequest = AddItemToCartBodyRequest(
                cartItem = CartItem(
                    sku = sku,
                    quantity = quantity,
                    quoteId = cartId
                )
            ))
            emit(Resource.Success<AddToCartResponse>(data.toAddToCartResponse()))
        } catch (e: HttpException) {
            emit(Resource.Error<AddToCartResponse>(e.localizedMessage ?: "API failure"))
        } catch (e: Exception) {
            emit(Resource.Error<AddToCartResponse>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}