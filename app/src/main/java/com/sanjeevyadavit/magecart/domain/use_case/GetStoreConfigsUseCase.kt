package com.sanjeevyadavit.magecart.domain.use_case

import com.sanjeevyadavit.magecart.common.Resource
import com.sanjeevyadavit.magecart.data.remote.dto.toStoreConfigs
import com.sanjeevyadavit.magecart.domain.model.StoreConfigs
import com.sanjeevyadavit.magecart.domain.repository.MageCartRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.util.concurrent.Flow
import javax.inject.Inject

class GetStoreConfigsUseCase @Inject constructor(private val repository: MageCartRepository) {
    operator fun invoke() = flow {
        try {
            emit(Resource.Loading<StoreConfigs>())
            val storeConfigs = repository.getStoreConfigs()
            if(storeConfigs.isEmpty()) throw Error("No store configs available")
            emit(Resource.Success<StoreConfigs>(storeConfigs[0].toStoreConfigs()))
        } catch (e: HttpException) {
            emit(Resource.Error<StoreConfigs>(e.localizedMessage ?: "API failure"))
        } catch(e: Exception) {
            emit(Resource.Error<StoreConfigs>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}