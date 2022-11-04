package com.sanjeevyadavit.magecart.domain.use_case

import com.sanjeevyadavit.magecart.common.Resource
import com.sanjeevyadavit.magecart.data.remote.dto.toCategory
import com.sanjeevyadavit.magecart.domain.model.Category
import com.sanjeevyadavit.magecart.domain.repository.MageCartRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetCategoryListUseCase @Inject constructor(private val repository: MageCartRepository) {
    operator fun invoke() = flow {
        try {
            emit(Resource.Loading<List<Category>>())
            val categories = repository.getCategories()
            // Return first childrenData which contains all The categories
            emit(Resource.Success<List<Category>>(categories.childrenData.map { it.toCategory() }))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Category>>(e.localizedMessage ?: "API failure"))
        } catch(e: Exception) {
            emit(Resource.Error<List<Category>>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}