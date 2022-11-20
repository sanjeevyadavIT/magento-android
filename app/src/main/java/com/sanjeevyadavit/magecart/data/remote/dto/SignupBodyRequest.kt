package com.sanjeevyadavit.magecart.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Customer(
    @SerializedName("email")  val email: String,
    @SerializedName("firstname")  val firstname: String,
    @SerializedName("lastname")  val lastname: String
)

data class SignupBodyRequest(
    @SerializedName("customer") val customer: Customer,
    @SerializedName("password") val password: String
)
