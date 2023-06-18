package com.sanjeevyadavit.magecart.domain.use_case

import com.sanjeevyadavit.magecart.common.model.Resource
import com.sanjeevyadavit.magecart.data.remote.dto.attribute.toAttributeData
import com.sanjeevyadavit.magecart.data.remote.dto.toCategory
import com.sanjeevyadavit.magecart.domain.model.AttributeData
import com.sanjeevyadavit.magecart.domain.model.Category
import com.sanjeevyadavit.magecart.domain.repository.MageCartRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetAttributeDataUseCase @Inject constructor(private val repository: MageCartRepository) {
    operator fun invoke(attributeId: Int) = flow {
        try {
            emit(Resource.Loading<AttributeData>())
            val data = repository.getAttributeData(attributeId)
            // Return first childrenData which contains all The categories
            emit(Resource.Success<AttributeData>(data.toAttributeData()))
        } catch (e: HttpException) {
            emit(Resource.Error<AttributeData>(e.localizedMessage ?: "API failure"))
        } catch(e: Exception) {
            emit(Resource.Error<AttributeData>(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}