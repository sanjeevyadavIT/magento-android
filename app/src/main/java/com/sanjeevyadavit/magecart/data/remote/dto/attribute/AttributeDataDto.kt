package com.sanjeevyadavit.magecart.data.remote.dto.attribute


import com.google.gson.annotations.SerializedName
import com.sanjeevyadavit.magecart.domain.model.AttributeData

data class AttributeDataDto(
    @SerializedName("apply_to")
    val applyTo: List<String>,
    @SerializedName("attribute_code")
    val attributeCode: String,
    @SerializedName("attribute_id")
    val attributeId: Int,
    @SerializedName("backend_model")
    val backendModel: String,
    @SerializedName("backend_type")
    val backendType: String,
    @SerializedName("default_frontend_label")
    val defaultFrontendLabel: String,
    @SerializedName("default_value")
    val defaultValue: String,
    @SerializedName("entity_type_id")
    val entityTypeId: String,
    @SerializedName("extension_attributes")
    val extensionAttributes: ExtensionAttributes,
    @SerializedName("frontend_input")
    val frontendInput: String,
    @SerializedName("frontend_labels")
    val frontendLabels: List<Any>,
    @SerializedName("is_comparable")
    val isComparable: String,
    @SerializedName("is_filterable")
    val isFilterable: Boolean,
    @SerializedName("is_filterable_in_grid")
    val isFilterableInGrid: Boolean,
    @SerializedName("is_filterable_in_search")
    val isFilterableInSearch: Boolean,
    @SerializedName("is_html_allowed_on_front")
    val isHtmlAllowedOnFront: Boolean,
    @SerializedName("is_required")
    val isRequired: Boolean,
    @SerializedName("is_searchable")
    val isSearchable: String,
    @SerializedName("is_unique")
    val isUnique: String,
    @SerializedName("is_used_for_promo_rules")
    val isUsedForPromoRules: String,
    @SerializedName("is_used_in_grid")
    val isUsedInGrid: Boolean,
    @SerializedName("is_user_defined")
    val isUserDefined: Boolean,
    @SerializedName("is_visible")
    val isVisible: Boolean,
    @SerializedName("is_visible_in_advanced_search")
    val isVisibleInAdvancedSearch: String,
    @SerializedName("is_visible_in_grid")
    val isVisibleInGrid: Boolean,
    @SerializedName("is_visible_on_front")
    val isVisibleOnFront: String,
    @SerializedName("is_wysiwyg_enabled")
    val isWysiwygEnabled: Boolean,
    @SerializedName("options")
    val options: List<Option>,
    @SerializedName("position")
    val position: Int,
    @SerializedName("scope")
    val scope: String,
    @SerializedName("source_model")
    val sourceModel: String,
    @SerializedName("used_for_sort_by")
    val usedForSortBy: Boolean,
    @SerializedName("used_in_product_listing")
    val usedInProductListing: String,
    @SerializedName("validation_rules")
    val validationRules: List<Any>
)

fun AttributeDataDto.toAttributeData(): AttributeData {
    val optionsMap = HashMap<String, String>()
    options.forEach{optionsMap[it.value] = it.label}

    return AttributeData(
        id = attributeId,
        code = attributeCode,
        options = optionsMap
    )
}