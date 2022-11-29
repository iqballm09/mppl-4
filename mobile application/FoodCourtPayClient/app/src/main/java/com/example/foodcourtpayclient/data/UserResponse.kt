package com.example.foodcourtpayclient.data

import com.google.gson.annotations.SerializedName

// register response
data class UserResponse(
    val user: User,
    val card: CardResponse
)

// user response
data class User(
    val phoneNumber: String? = null,
    val name: String? = null,
    val id: Int? = null,
    val email: String? = null,
)

// card response
data class CardResponse(
    val id: Int? = null,
    val UserID: Int? = null,
    val name: String? = null,
    val pinNumber: String? = null,
    val saldo: Int? = null,
)

// login response
data class LoginResponse(
    val name: String,
    var id: Int,
    val token: String
)

// transaction response
data class TransactionResponse(
    val payment: PaymentResponse
)

data class PaymentResponse (
    val amount: String,
    val foodcourtName: String,
    val merchantName: String,
    val date: String
)

// list transaction response
data class ListTransactionResponse(
    val payments: List<PaymentsResponse>
)

data class PaymentsResponse (
    val amount: String,
    val foodcourtName: String,
    val merchantName: String,
    val date: String
)