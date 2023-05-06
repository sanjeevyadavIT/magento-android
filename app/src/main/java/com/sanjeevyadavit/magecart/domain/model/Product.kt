package com.sanjeevyadavit.magecart.domain.model

data class Product(
    val id: Int,
    val name: String,
    val sku: String,
    val price: Int,
    val thumbnailUrl: String?,
    val mediaList: List<String>?
)
