package com.sanjeevyadavit.magecart.data.remote.dto.profile


import com.google.gson.annotations.SerializedName

data class Region(
    @SerializedName("region")
    val region: String?,
    @SerializedName("region_code")
    val regionCode: String?,
    @SerializedName("region_id")
    val regionId: Int?
)