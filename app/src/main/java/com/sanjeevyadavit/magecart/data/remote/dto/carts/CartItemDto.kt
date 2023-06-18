package com.sanjeevyadavit.magecart.data.remote.dto.carts


import com.google.gson.annotations.SerializedName
import com.sanjeevyadavit.magecart.domain.model.AddToCartResponse

data class CartItemDto(
    @SerializedName("item_id")
    val itemId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("product_type")
    val productType: String,
    @SerializedName("qty")
    val qty: Int,
    @SerializedName("quote_id")
    val quoteId: String,
    @SerializedName("sku")
    val sku: String
)

fun CartItemDto.toAddToCartResponse(): AddToCartResponse {
    return AddToCartResponse(
        itemId = itemId,
        name = name,
        price = price,
        productType = productType,
        qty = qty,
        quoteId = quoteId,
        sku = sku
    )
}