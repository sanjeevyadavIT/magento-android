package com.sanjeevyadavit.magecart.domain.use_case

import com.sanjeevyadavit.magecart.common.model.Resource
import com.sanjeevyadavit.magecart.data.remote.dto.LoginBodyRequest
import com.sanjeevyadavit.magecart.domain.repository.MageCartRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: MageCartRepository) {
    operator fun invoke(email: String, password: String) = flow {
        try {
            emit(Resource.Loading<String>())
            val accessToken = repository.login(LoginBodyRequest(email, password))
            emit(Resource.Success<String>(accessToken))
        } catch (e: HttpException) {
            emit(Resource.Error<String>(e.localizedMessage ?: "API failure"))
        } catch(e: Exception) {
            emit(Resource.Error<String>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}