package com.sanjeevyadavit.magecart.domain.model

data class AttributeData(
    val id: Int,
    val code: String,
    val options: HashMap<String, String>
)
