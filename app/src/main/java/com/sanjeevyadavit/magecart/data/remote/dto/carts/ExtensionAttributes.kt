package com.sanjeevyadavit.magecart.data.remote.dto.carts


import com.google.gson.annotations.SerializedName

data class ExtensionAttributes(
    @SerializedName("is_subscribed")
    val isSubscribed: Boolean
)