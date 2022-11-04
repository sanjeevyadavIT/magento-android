package com.sanjeevyadavit.magecart.common

data class IState<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
