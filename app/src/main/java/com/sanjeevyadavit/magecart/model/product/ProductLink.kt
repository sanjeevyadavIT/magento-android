package com.sanjeevyadavit.magecart.model.product


import com.google.gson.annotations.SerializedName

data class ProductLink(
    @SerializedName("link_type")
    val linkType: String,
    @SerializedName("linked_product_sku")
    val linkedProductSku: String,
    @SerializedName("linked_product_type")
    val linkedProductType: String,
    @SerializedName("position")
    val position: Int,
    @SerializedName("sku")
    val sku: String
)