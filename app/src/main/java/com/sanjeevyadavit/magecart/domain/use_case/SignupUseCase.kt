package com.sanjeevyadavit.magecart.domain.use_case

import com.sanjeevyadavit.magecart.common.model.Resource
import com.sanjeevyadavit.magecart.data.remote.dto.*
import com.sanjeevyadavit.magecart.domain.model.SignupResponse
import com.sanjeevyadavit.magecart.domain.repository.MageCartRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class SignupUseCase @Inject constructor(private val repository: MageCartRepository) {
    operator fun invoke(firstName: String, lastName: String, email: String, password: String) = flow {
        try {
            emit(Resource.Loading<SignupResponse>())
            val customer = Customer(email, firstName, lastName)
            val response = repository.signup(SignupBodyRequest(customer, password))
            emit(Resource.Success<SignupResponse>(response.toSignupResponse()))
        } catch (e: HttpException) {
            emit(Resource.Error<SignupResponse>(e.localizedMessage ?: "API failure"))
        } catch(e: Exception) {
            emit(Resource.Error<SignupResponse>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}