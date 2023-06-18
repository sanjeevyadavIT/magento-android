package com.sanjeevyadavit.magecart.domain.model

import com.sanjeevyadavit.magecart.common.ProductType

data class ProductDetail(
    val id: Int,
    val name: String,
    val sku: String,
    val price: Int,
    val thumbnailUrl: String?,
    val mediaList: List<String>?,
    val description: String?,
    val productType: ProductType,
    val configurableOptions: List<ConfigurableOption>?
)

data class ConfigurableOption(
    val id: Int,
    val attributeId: String,
    val label: String,
    val values: List<Int>
)

