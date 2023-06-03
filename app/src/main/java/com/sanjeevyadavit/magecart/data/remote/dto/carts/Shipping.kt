package com.sanjeevyadavit.magecart.data.remote.dto.carts


import com.google.gson.annotations.SerializedName

data class Shipping(
    @SerializedName("address")
    val address: Address,
    @SerializedName("method")
    val method: Any
)