package com.sanjeevyadavit.magecart.common.model

data class IState<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
