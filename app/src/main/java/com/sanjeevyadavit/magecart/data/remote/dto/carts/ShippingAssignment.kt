package com.sanjeevyadavit.magecart.data.remote.dto.carts


import com.google.gson.annotations.SerializedName

data class ShippingAssignment(
    @SerializedName("items")
    val items: List<ItemInCartDto>,
    @SerializedName("shipping")
    val shipping: Shipping
)