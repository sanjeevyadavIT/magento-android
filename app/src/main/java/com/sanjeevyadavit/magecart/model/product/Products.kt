package com.sanjeevyadavit.magecart.model.product


import com.google.gson.annotations.SerializedName

data class Products(
    @SerializedName("items")
    val items: List<ProductData>,
    @SerializedName("search_criteria")
    val searchCriteria: SearchCriteria,
    @SerializedName("total_count")
    val totalCount: Int
)