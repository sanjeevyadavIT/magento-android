package com.sanjeevyadavit.magecart.data.remote.dto.carts


import com.google.gson.annotations.SerializedName

data class Customer(
    @SerializedName("addresses")
    val addresses: List<Any>,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("created_in")
    val createdIn: String,
    @SerializedName("disable_auto_group_change")
    val disableAutoGroupChange: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("extension_attributes")
    val extensionAttributes: ExtensionAttributes,
    @SerializedName("firstname")
    val firstname: String,
    @SerializedName("group_id")
    val groupId: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lastname")
    val lastname: String,
    @SerializedName("store_id")
    val storeId: Int,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("website_id")
    val websiteId: Int
)