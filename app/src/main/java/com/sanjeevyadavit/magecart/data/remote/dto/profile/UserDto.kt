package com.sanjeevyadavit.magecart.data.remote.dto.profile


import com.google.gson.annotations.SerializedName
import com.sanjeevyadavit.magecart.domain.model.Address
import com.sanjeevyadavit.magecart.domain.model.Region
import com.sanjeevyadavit.magecart.domain.model.User

data class UserDto(
    @SerializedName("addresses")
    val addresses: List<Addresse>?,
    @SerializedName("created_at")
    val createdAt: String?,
    @SerializedName("created_in")
    val createdIn: String?,
    @SerializedName("default_billing")
    val defaultBilling: String?,
    @SerializedName("default_shipping")
    val defaultShipping: String?,
    @SerializedName("disable_auto_group_change")
    val disableAutoGroupChange: Int?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("extension_attributes")
    val extensionAttributes: ExtensionAttributes?,
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("group_id")
    val groupId: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("lastname")
    val lastname: String?,
    @SerializedName("store_id")
    val storeId: Int?,
    @SerializedName("updated_at")
    val updatedAt: String?,
    @SerializedName("website_id")
    val websiteId: Int?
)

fun UserDto.toUser(): User {
    val addresses = addresses?.map { addressDto ->
        Address(
            id = addressDto.id ?: 0,
            customerId = addressDto.customerId ?: 0,
            region = addressDto.region?.let { Region(it.regionCode ?: "", it.region ?: "", it.regionId ?: 0) } ?: Region("", "", 0),
            regionId = addressDto.regionId ?: 0,
            countryId = addressDto.countryId ?: "",
            street = addressDto.street ?: emptyList(),
            telephone = addressDto.telephone ?: "",
            postcode = addressDto.postcode ?: "",
            city = addressDto.city ?: "",
            firstname = addressDto.firstname ?: "",
            lastname = addressDto.lastname ?: "",
            defaultShipping = addressDto.defaultShipping ?: false,
            defaultBilling = addressDto.defaultBilling ?: false
        )
    } ?: emptyList()

    return User(
        id = id ?: 0,
        defaultBilling = defaultBilling ?: "",
        defaultShipping = defaultShipping ?: "",
        email = email ?: "",
        firstname = firstname ?: "",
        lastname = lastname ?: "",
        storeId = storeId ?: 0,
        addresses = addresses
    )
}