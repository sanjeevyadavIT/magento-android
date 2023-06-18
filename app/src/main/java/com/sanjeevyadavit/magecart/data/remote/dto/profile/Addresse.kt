package com.sanjeevyadavit.magecart.data.remote.dto.profile


import com.google.gson.annotations.SerializedName

data class Addresse(
    @SerializedName("city")
    val city: String?,
    @SerializedName("country_id")
    val countryId: String?,
    @SerializedName("customer_id")
    val customerId: Int?,
    @SerializedName("default_billing")
    val defaultBilling: Boolean?,
    @SerializedName("default_shipping")
    val defaultShipping: Boolean?,
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("lastname")
    val lastname: String?,
    @SerializedName("postcode")
    val postcode: String?,
    @SerializedName("region")
    val region: Region?,
    @SerializedName("region_id")
    val regionId: Int?,
    @SerializedName("street")
    val street: List<String>?,
    @SerializedName("telephone")
    val telephone: String?
)
