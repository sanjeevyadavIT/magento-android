package com.sanjeevyadavit.magecart.data.remote.dto.carts


import com.google.gson.annotations.SerializedName

data class ExtensionAttributesX(
    @SerializedName("shipping_assignments")
    val shippingAssignments: List<ShippingAssignment>
)