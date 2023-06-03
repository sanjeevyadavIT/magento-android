package com.sanjeevyadavit.magecart.domain.model

import com.sanjeevyadavit.magecart.common.ProductType

data class Cart(
    val id: Int,
    val itemsCount: Int, // Unique number of item
    val itemsQuantity: Int, // Total number of items in cart
    val items: List<CartItem>
)

data class CartItem(
    val itemId: Int,
    val sku: String,
    val quantity: Int,
    val name: String,
    val price: Int,
    val productType: ProductType,
    val quoteId: Int,
)
