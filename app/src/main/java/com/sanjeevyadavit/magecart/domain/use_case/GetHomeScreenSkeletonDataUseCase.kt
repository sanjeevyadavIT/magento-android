package com.sanjeevyadavit.magecart.domain.use_case

import com.sanjeevyadavit.magecart.BuildConfig
import com.sanjeevyadavit.magecart.common.Resource
import com.sanjeevyadavit.magecart.data.remote.dto.toHomeScreenSkeletonData
import com.sanjeevyadavit.magecart.domain.model.HomeScreenSkeletonData
import com.sanjeevyadavit.magecart.domain.repository.MageCartRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetHomeScreenSkeletonDataUseCase @Inject constructor(private val repository: MageCartRepository) {
    operator fun invoke() = flow {
        try {
            emit(Resource.Loading<HomeScreenSkeletonData>())
            val cmsData = repository.getCmsBlock(BuildConfig.HOME_CMS_BLOCK_ID)
            // Return first childrenData which contains all The categories
            emit(Resource.Success<HomeScreenSkeletonData>(cmsData.toHomeScreenSkeletonData()))
        } catch (e: HttpException) {
            emit(Resource.Error<HomeScreenSkeletonData>(e.localizedMessage ?: "API failure"))
        } catch(e: Exception) {
            emit(Resource.Error<HomeScreenSkeletonData>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}