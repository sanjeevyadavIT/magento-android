package com.sanjeevyadavit.magecart.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.sanjeevyadavit.magecart.domain.model.SignupResponse

// QUESTION: How to extend this class to have Customer class keys
data class SignupDto(
    @SerializedName("id") val id: Int,
    @SerializedName("email") val email: String,
    @SerializedName("firstname") val firstname: String,
    @SerializedName("lastname") val lastname: String
)

fun SignupDto.toSignupResponse(): SignupResponse = SignupResponse(
    id = id,
    email = email,
    firstname = firstname,
    lastname = lastname
)