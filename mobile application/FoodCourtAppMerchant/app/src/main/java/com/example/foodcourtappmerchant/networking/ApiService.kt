package com.example.foodcourtappmerchant.networking

import com.example.foodcourtappmerchant.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("merchants/login")
    fun postLogin(@Body request: LoginRequest): Call<LoginResponse>

    @GET("merchants/id")
    fun getCard(
        @Header("auth-token") header: String?
    ): Call<UserResponse>

    @GET("merchants/id/transactions")
    fun getTransactions(
        @Header("auth-token") header: String?
    ): Call<ListTransactionResponse>

    @GET("withdraws/merchantID")
    fun getWithdraws(
        @Header("auth-token") header: String?
    ): Call<ListWithdrawsRespose>

    @POST("withdraws/merchantID")
    fun postWithdraw(
        @Header("auth-token") header: String?,
        @Body request: WithdrawRequest
    ): Call <WithdrawResponseParent>
}