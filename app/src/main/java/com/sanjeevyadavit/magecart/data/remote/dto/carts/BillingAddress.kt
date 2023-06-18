package com.sanjeevyadavit.magecart.data.remote.dto.carts


import com.google.gson.annotations.SerializedName

data class BillingAddress(
    @SerializedName("city")
    val city: Any,
    @SerializedName("country_id")
    val countryId: Any,
    @SerializedName("customer_id")
    val customerId: Int,
    @SerializedName("email")
    val email: String,
    @SerializedName("firstname")
    val firstname: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lastname")
    val lastname: Any,
    @SerializedName("postcode")
    val postcode: Any,
    @SerializedName("region")
    val region: Any,
    @SerializedName("region_code")
    val regionCode: Any,
    @SerializedName("region_id")
    val regionId: Any,
    @SerializedName("same_as_billing")
    val sameAsBilling: Int,
    @SerializedName("save_in_address_book")
    val saveInAddressBook: Int,
    @SerializedName("street")
    val street: List<String>,
    @SerializedName("telephone")
    val telephone: Any
)