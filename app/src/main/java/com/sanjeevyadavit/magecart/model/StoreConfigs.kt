package com.sanjeevyadavit.magecart.model


import com.google.gson.annotations.SerializedName

data class StoreConfigs(
    @SerializedName("base_currency_code")
    val baseCurrencyCode: String,
    @SerializedName("base_link_url")
    val baseLinkUrl: String,
    @SerializedName("base_media_url")
    val baseMediaUrl: String,
    @SerializedName("base_static_url")
    val baseStaticUrl: String,
    @SerializedName("base_url")
    val baseUrl: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("default_display_currency_code")
    val defaultDisplayCurrencyCode: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("locale")
    val locale: String,
    @SerializedName("secure_base_link_url")
    val secureBaseLinkUrl: String,
    @SerializedName("secure_base_media_url")
    val secureBaseMediaUrl: String,
    @SerializedName("secure_base_static_url")
    val secureBaseStaticUrl: String,
    @SerializedName("secure_base_url")
    val secureBaseUrl: String,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("website_id")
    val websiteId: Int,
    @SerializedName("weight_unit")
    val weightUnit: String
)