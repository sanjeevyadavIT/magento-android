package com.sanjeevyadavit.magecart.model.product


import com.google.gson.annotations.SerializedName

data class MediaGalleryEntry(
    @SerializedName("disabled")
    val disabled: Boolean,
    @SerializedName("file")
    val `file`: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("label")
    val label: String,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("position")
    val position: Int,
    @SerializedName("types")
    val types: List<String>
)