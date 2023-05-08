package com.sanjeevyadavit.magecart.common

private const val CONFIGURABLE_PRODUCT_TYPE = "configurable"
private const val SIMPLE_PRODUCT_TYPE = "simple"

object Constants {
    const val BASE_URL = "https://magento2-demo.magebit.com/"
    const val THUMBNAIL_SK = "thumbnail"
    const val PAGE_SIZE = 10
}

enum class ProductType(val value: String) {
    CONFIGURABLE(CONFIGURABLE_PRODUCT_TYPE),
    SIMPLE(SIMPLE_PRODUCT_TYPE),
    UNKNOWN("unknown")
}

// QUESTION: Is there any way I can add this function as extension function to enum class and access outside
fun getProductTypeFromString(str: String): ProductType {
    return when (str) {
        CONFIGURABLE_PRODUCT_TYPE -> ProductType.CONFIGURABLE
        SIMPLE_PRODUCT_TYPE -> ProductType.SIMPLE
        else -> ProductType.UNKNOWN
    }
}