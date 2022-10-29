package com.sanjeevyadavit.magecart.model.product


import com.google.gson.annotations.SerializedName

data class CategoryLink(
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("position")
    val position: Int
)