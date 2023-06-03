package com.sanjeevyadavit.magecart.domain.use_case

import com.sanjeevyadavit.magecart.common.model.Resource
import com.sanjeevyadavit.magecart.data.remote.dto.carts.toCart
import com.sanjeevyadavit.magecart.domain.model.Cart
import com.sanjeevyadavit.magecart.domain.repository.MageCartRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetCustomerCartUseCase @Inject constructor(private val repository: MageCartRepository){
    operator fun invoke(customerSessionToken: String, firsTimeLoggedIn: Boolean = false) = flow {
        try {
            val token = "Bearer $customerSessionToken"
            emit(Resource.Loading<Cart>())
            if(firsTimeLoggedIn) {
                var cartId = repository.createQuoteId(token) // Creates Quote ID if user is first time logged in, and there is no cart associated with the account
            }
            val cart = repository.getCustomerCart(token)
            // Return first childrenData which contains all The categories
            emit(Resource.Success<Cart>(cart.toCart()))
        } catch (e: HttpException) {
            emit(Resource.Error<Cart>(e.localizedMessage ?: "API failure"))
        } catch(e: Exception) {
            emit(Resource.Error<Cart>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}