package com.sanjeevyadavit.magecart.data.remote.dto.carts

import com.google.gson.annotations.SerializedName

data class AddItemToCartBodyRequest(
    @SerializedName("cartItem") val cartItem: CartItem,
)

data class CartItem(
    @SerializedName("sku") val sku: String,
    @SerializedName("qty") val quantity: Int,
    @SerializedName("quote_id") val quoteId: Int,
)