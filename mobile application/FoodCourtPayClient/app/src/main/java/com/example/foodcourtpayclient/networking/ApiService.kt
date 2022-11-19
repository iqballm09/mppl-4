package com.example.foodcourtpayclient.networking

import com.example.foodcourtpayclient.data.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("users/register")
    fun registerUser(@Body request: UserRequest): Call<UserResponse>

    @POST("users/login")
    fun postLogin(@Body request: LoginRequest): Call<LoginResponse>

    @GET("cards/id/userID")
    fun getCard(
        @Header("auth-token") header: String?
    ): Call <UserResponse>
}