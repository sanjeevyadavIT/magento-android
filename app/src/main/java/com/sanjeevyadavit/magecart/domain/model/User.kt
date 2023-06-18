package com.sanjeevyadavit.magecart.domain.model

data class User(
    val id: Int,
    val defaultBilling: String,
    val defaultShipping: String,
    val email: String,
    val firstname: String,
    val lastname: String,
    val storeId: Int,
    val addresses: List<Address>,
)

data class Address(
    val id: Int,
    val customerId: Int,
    val region: Region,
    val regionId: Int,
    val countryId: String,
    val street: List<String>,
    val telephone: String,
    val postcode: String,
    val city: String,
    val firstname: String,
    val lastname: String,
    val defaultShipping: Boolean,
    val defaultBilling: Boolean
)

data class Region(
    val regionCode: String,
    val region: String,
    val regionId: Int
)
