package com.sanjeevyadavit.magecart.data.remote.dto.product


import com.google.gson.annotations.SerializedName

data class ProductsDto(
    @SerializedName("items")
    val items: List<ProductDto>,
    @SerializedName("search_criteria")
    val searchCriteria: SearchCriteria,
    @SerializedName("total_count")
    val totalCount: Int
)