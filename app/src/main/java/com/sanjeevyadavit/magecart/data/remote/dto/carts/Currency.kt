package com.sanjeevyadavit.magecart.data.remote.dto.carts


import com.google.gson.annotations.SerializedName

data class Currency(
    @SerializedName("base_currency_code")
    val baseCurrencyCode: String,
    @SerializedName("base_to_global_rate")
    val baseToGlobalRate: Int,
    @SerializedName("base_to_quote_rate")
    val baseToQuoteRate: Int,
    @SerializedName("global_currency_code")
    val globalCurrencyCode: String,
    @SerializedName("quote_currency_code")
    val quoteCurrencyCode: String,
    @SerializedName("store_currency_code")
    val storeCurrencyCode: String,
    @SerializedName("store_to_base_rate")
    val storeToBaseRate: Int,
    @SerializedName("store_to_quote_rate")
    val storeToQuoteRate: Int
)