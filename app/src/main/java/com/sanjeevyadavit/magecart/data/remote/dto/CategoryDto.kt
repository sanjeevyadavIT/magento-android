package com.sanjeevyadavit.magecart.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.sanjeevyadavit.magecart.domain.model.Category

data class CategoriesDto(
    @SerializedName("children_data")
    val childrenData: List<CategoriesDto>,
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

fun CategoriesDto.toCategory(): Category = Category(
    childrenData = childrenData.map { it.toCategory() },
    id = id,
    isActive = isActive,
    level = level,
    name = name,
    productCount = productCount
)