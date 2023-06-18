package com.sanjeevyadavit.magecart.data.remote.dto.profile


import com.google.gson.annotations.SerializedName

data class ExtensionAttributes(
    @SerializedName("is_subscribed")
    val isSubscribed: Boolean?
)