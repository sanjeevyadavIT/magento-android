package com.sanjeevyadavit.magecart.data.remote.dto.attribute


import com.google.gson.annotations.SerializedName

data class Option(
    @SerializedName("label")
    val label: String,
    @SerializedName("value")
    val value: String
)