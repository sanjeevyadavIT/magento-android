package com.sanjeevyadavit.magecart.data.remote.dto.product


import com.google.gson.annotations.SerializedName

data class CustomAttribute(
    @SerializedName("attribute_code")
    val attributeCode: String,
    @SerializedName("value")
    val value: Any // This is supposed to be string, but response giving String and Array
)