package com.example.skyfysample.data.api.weather

import com.example.skyfysample.model.weather.DailyAqiWeatherRs
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AqiAPI {
    @GET("v1/air-quality")
    suspend fun getDailyAqiInfo(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("current") current: String = "european_aqi,pm2_5,uv_index",
        @Query("forecast_days") forecastDays: String = "1"
    ): Response<DailyAqiWeatherRs>
}