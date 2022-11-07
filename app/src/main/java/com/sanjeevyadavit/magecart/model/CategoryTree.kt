package com.sanjeevyadavit.magecart.model


import com.google.gson.annotations.SerializedName

data class CategoryTree(
    @SerializedName("children_data")
    val childrenData: List<CategoryTree>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("level")
    val level: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("parent_id")
    val parentId: Int,
    @SerializedName("position")
    val position: Int,
    @SerializedName("product_count")
    val productCount: Int
)