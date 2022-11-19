package com.example.foodcourtpayclient.networking

import com.example.foodcourtpayclient.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiConfig {
            private val loggingInterceptor = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            } else {
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
            }
            private val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
            private val retrofit = Retrofit.Builder()
                .baseUrl("https://foodpayapi.up.railway.app/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

    fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
        }
}