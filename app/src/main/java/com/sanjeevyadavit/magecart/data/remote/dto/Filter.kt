package com.sanjeevyadavit.magecart.data.remote.dto

data class Filter(
    val field: String,
    val value: Int,
    val conditionType: String = "eq",
)
