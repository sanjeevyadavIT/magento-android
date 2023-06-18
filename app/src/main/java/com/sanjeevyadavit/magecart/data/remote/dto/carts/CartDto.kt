package com.sanjeevyadavit.magecart.data.remote.dto.carts

import com.google.gson.annotations.SerializedName
import com.sanjeevyadavit.magecart.domain.model.Cart

data class CartDto (
    @SerializedName("id")
    val id: Int
)

fun CartDto.toCart(): Cart {
    return Cart(id = id)
}