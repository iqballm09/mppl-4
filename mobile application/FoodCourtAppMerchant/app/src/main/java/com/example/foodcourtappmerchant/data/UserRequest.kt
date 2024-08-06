package com.example.foodcourtappmerchant.data

data class UserRequest (
    val name:String? = null,
    val email: String?= null,
    val password: String? = null,
    val phoneNumber: String? = null,
    val pinNumber: String? = null
)

data class LoginRequest (
    var email: String,
    var password: String
)

data class WithdrawRequest (
    val amount: String?,
    val method: String?,
    val accountNumber: String?,
    val pinNumber: String
)