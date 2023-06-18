package com.sanjeevyadavit.magecart.domain.use_case

import com.sanjeevyadavit.magecart.common.model.Resource
import com.sanjeevyadavit.magecart.data.remote.dto.profile.toUser
import com.sanjeevyadavit.magecart.domain.model.User
import com.sanjeevyadavit.magecart.domain.repository.MageCartRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetLoggedInUserDetailsUseCase @Inject constructor(private val repository: MageCartRepository) {

    operator fun invoke(customerSessionToken: String) = flow {
        try {
            val token = "Bearer $customerSessionToken"
            emit(Resource.Loading<User>())
            val data = repository.fetchLoggedInCustomerDetails(
                authorizationToken = token,
            )
            emit(Resource.Success<User>(data.toUser()))
        } catch (e: HttpException) {
            emit(Resource.Error<User>(e.localizedMessage ?: "API failure"))
        } catch (e: Exception) {
            emit(Resource.Error<User>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}