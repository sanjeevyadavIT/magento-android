package com.sanjeevyadavit.magecart.data.remote.dto.product


import com.google.gson.annotations.SerializedName

data class SearchCriteria(
    @SerializedName("current_page")
    val currentPage: Int,
    @SerializedName("page_size")
    val pageSize: Int
)