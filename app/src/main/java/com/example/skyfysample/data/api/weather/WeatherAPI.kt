package com.example.skyfysample.data.api.weather

import com.example.skyfysample.model.weather.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("v1/forecast")
    suspend fun getWeatherInfo(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("daily") daily: String = "weathercode,temperature_2m_max,sunset,uv_index_max",
        @Query("timezone") timezone: String = "auto"
    ): Response<WeatherResponse>
}