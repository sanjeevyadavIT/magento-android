package com.sanjeevyadavit.magecart.model.product


import com.google.gson.annotations.SerializedName

data class ConfigurableProductOption(
    @SerializedName("attribute_id")
    val attributeId: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("label")
    val label: String,
    @SerializedName("position")
    val position: Int,
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("values")
    val values: List<Value>
)