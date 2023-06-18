package com.sanjeevyadavit.magecart.data.remote.dto.product


import com.google.gson.annotations.SerializedName
import com.sanjeevyadavit.magecart.common.Constants
import com.sanjeevyadavit.magecart.common.getProductTypeFromString
import com.sanjeevyadavit.magecart.domain.model.ConfigurableOption
import com.sanjeevyadavit.magecart.domain.model.Product
import com.sanjeevyadavit.magecart.domain.model.ProductDetail

data class ProductDto(
    @SerializedName("attribute_set_id")
    val attributeSetId: Int,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("custom_attributes")
    val customAttributes: List<CustomAttribute>,
    @SerializedName("extension_attributes")
    val extensionAttributes: ExtensionAttributes,
    @SerializedName("id")
    val id: Int,
    @SerializedName("media_gallery_entries")
    val mediaGalleryEntries: List<MediaGalleryEntry>,
    @SerializedName("name")
    val name: String,
    @SerializedName("options")
    val options: List<Any>,
    @SerializedName("price")
    val price: Int,
    @SerializedName("product_links")
    val productLinks: List<ProductLink>,
    @SerializedName("sku")
    val sku: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("tier_prices")
    val tierPrices: List<Any>,
    @SerializedName("type_id")
    val typeId: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("visibility")
    val visibility: Int
)

fun ProductDto.toProduct(): Product {
    val thumbnailUrl = customAttributes.find {
        it.attributeCode == Constants.THUMBNAIL_SK
    }?.value as? String

    return Product(
        id = id,
        name = name,
        sku = sku,
        price = price,
        thumbnailUrl = thumbnailUrl,
        mediaList = mediaGalleryEntries.map { it.file }
    )
}

fun ProductDto.toProductDetail(): ProductDetail {
    val product = toProduct()
    val description = customAttributes.find { it.attributeCode == "description" }?.value as String?
    val configurableOptions =
        extensionAttributes?.configurableProductOptions?.sortedBy { it.position }?.map {
            ConfigurableOption(
                id = it.id,
                attributeId = it.attributeId,
                label = it.label,
                values = it.values.map { it.valueIndex })
        }

    return ProductDetail(
        id = product.id,
        name = product.name,
        sku = product.sku,
        price = product.price,
        thumbnailUrl = product.thumbnailUrl,
        mediaList = product.mediaList,
        description = description,
        productType = getProductTypeFromString(typeId),
        configurableOptions = configurableOptions
    )
}