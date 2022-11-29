package com.example.foodcourtappmerchant.data

data class UserResponse (
    val merchant: Merchant
)

data class Merchant (
    val name: String? = null,
    val id: Int? = null,
    val merchantName: String? = null,
    val foodCourtName: String? = null,
    val phoneNumber: String? = null,
    val pinNumber: String? = null,
    val income: Int? = null
)

data class LoginResponse (
    val name: String,
    val id: Int,
    val token: String
)
