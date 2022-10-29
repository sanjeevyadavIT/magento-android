package com.sanjeevyadavit.magecart.model.product


import com.google.gson.annotations.SerializedName

data class ExtensionAttributes(
    @SerializedName("category_links")
    val categoryLinks: List<CategoryLink>,
    @SerializedName("configurable_product_links")
    val configurableProductLinks: List<Int>,
    @SerializedName("configurable_product_options")
    val configurableProductOptions: List<ConfigurableProductOption>,
    @SerializedName("website_ids")
    val websiteIds: List<Int>
)