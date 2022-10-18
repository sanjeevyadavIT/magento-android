package com.sanjeevyadavit.magecart.model

data class Customer(
    val email: String,
    val firstname: String,
    val lastname: String
)

data class SignupBodyRequest(
    val customer: Customer, val password: String
)
