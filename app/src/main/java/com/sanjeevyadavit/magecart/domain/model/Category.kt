package com.sanjeevyadavit.magecart.domain.model

import com.google.gson.annotations.SerializedName
import com.sanjeevyadavit.magecart.data.remote.dto.CategoriesDto

data class Category(
    val childrenData: List<Category>,
    val id: Int,
    val isActive: Boolean,
    val level: Int,
    val name: String,
    val productCount: Int
)