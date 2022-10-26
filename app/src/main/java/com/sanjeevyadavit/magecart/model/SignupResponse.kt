package com.sanjeevyadavit.magecart.model

import com.google.gson.annotations.SerializedName

// QUESTION: How to extend this class to have Customer class keys
data class SignupResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("firstname") val firstname: String,
    @SerializedName("lastname") val lastname: String
)
