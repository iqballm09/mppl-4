package com.example.foodcourtappmerchant.data

data class UserResponse (
    val merchant: Merchant
)

data class Merchant (
    val name: String? = null,
    val id: Int? = null,
    val income: Int? = null
)

data class LoginResponse (
    val name: String,
    val id: Int,
    val token: String
)

// list transaction response
data class ListTransactionResponse(
    val payments: List<PaymentsResponse>
)

data class PaymentsResponse (
    val name_user: String,
    val amount: String,
    val foodcourtName: String,
    val merchantName: String,
    val date: String
)

// withdraws response
data class ListWithdrawsRespose (
    val withdraws: List<WithdrawsResponse>
        )

data class WithdrawsResponse  (
    val method: String,
    val accountNumber: String,
    val amount: String,
    val date: String
)

// post withdraws response
data class WithdrawResponseParent (
    val withdraw: WithdrawResponse
)

data class WithdrawResponse (
    val method: String,
    val accountNumber: String,
    val amount: String,
    val date: String
)