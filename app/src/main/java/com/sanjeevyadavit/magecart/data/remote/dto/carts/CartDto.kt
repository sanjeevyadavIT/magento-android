package com.sanjeevyadavit.magecart.data.remote.dto.carts


import com.google.gson.annotations.SerializedName
import com.sanjeevyadavit.magecart.common.ProductType
import com.sanjeevyadavit.magecart.common.getProductTypeFromString
import com.sanjeevyadavit.magecart.domain.model.Cart
import com.sanjeevyadavit.magecart.domain.model.CartItem

data class CartDto(
    @SerializedName("billing_address")
    val billingAddress: BillingAddress,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("currency")
    val currency: Currency,
    @SerializedName("customer")
    val customer: Customer,
    @SerializedName("customer_is_guest")
    val customerIsGuest: Boolean,
    @SerializedName("customer_note_notify")
    val customerNoteNotify: Boolean,
    @SerializedName("customer_tax_class_id")
    val customerTaxClassId: Int,
    @SerializedName("extension_attributes")
    val extensionAttributes: ExtensionAttributesX,
    @SerializedName("id")
    val id: Int,
    @SerializedName("is_active")
    val isActive: Boolean,
    @SerializedName("is_virtual")
    val isVirtual: Boolean,
    @SerializedName("items")
    val items: List<ItemInCartDto>,
    @SerializedName("items_count")
    val itemsCount: Int,
    @SerializedName("items_qty")
    val itemsQty: Int,
    @SerializedName("orig_order_id")
    val origOrderId: Int,
    @SerializedName("store_id")
    val storeId: Int,
    @SerializedName("updated_at")
    val updatedAt: String
)

fun CartDto.toCart(): Cart {
    return Cart(
        id = id,
        itemsCount = itemsCount,
        itemsQuantity = itemsQty,
        items = items.map { _item ->
            CartItem(
                itemId = _item.itemId,
                sku = _item.sku,
                quantity = _item.qty,
                name = _item.name,
                price = _item.price,
                productType = getProductTypeFromString(_item.productType),
                quoteId = _item.quoteId.toInt()
            )
        }
    )
}