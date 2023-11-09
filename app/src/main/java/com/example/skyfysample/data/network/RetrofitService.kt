package com.example.skyfysample.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object {
        private const val BASE_URL = "https://api.open-meteo.com/"
        private const val BASE_AQI_URL = "https://air-quality-api.open-meteo.com/"

        private val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val aqiRetrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_AQI_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}