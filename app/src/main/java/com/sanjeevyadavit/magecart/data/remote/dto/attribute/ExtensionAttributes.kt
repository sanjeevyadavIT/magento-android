package com.sanjeevyadavit.magecart.data.remote.dto.attribute


import com.google.gson.annotations.SerializedName

data class ExtensionAttributes(
    @SerializedName("is_pagebuilder_enabled")
    val isPagebuilderEnabled: Boolean
)