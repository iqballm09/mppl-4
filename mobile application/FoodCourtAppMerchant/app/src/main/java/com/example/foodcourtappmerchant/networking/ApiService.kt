package com.example.foodcourtappmerchant.networking

import com.example.foodcourtappmerchant.data.LoginRequest
import com.example.foodcourtappmerchant.data.LoginResponse
import com.example.foodcourtappmerchant.data.UserResponse
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
}