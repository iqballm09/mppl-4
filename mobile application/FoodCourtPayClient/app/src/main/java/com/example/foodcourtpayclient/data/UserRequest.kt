package com.example.foodcourtpayclient.data

data class UserRequest (
    val name: String?= null,
    val email: String?= null,
    val password: String?= null,
    val phoneNumber: String?= null,
    val pinNumber: String?= null
)

data class TransactionRequest (
    val amount: String?,
    val merchantID: String?,
    val pinNumber: String
        )

data class  LoginRequest (
    var email: String,
    var password: String
)