package com.example.skyfysample.data.repository.weather

import com.example.skyfysample.data.api.weather.AqiAPI
import com.example.skyfysample.data.network.RetrofitService
import com.example.skyfysample.data.repository.DataResult
import com.example.skyfysample.model.dto.DailyAqiWeatherDto
import com.example.skyfysample.model.dto.toDto

class AqiRepository {

    private val apiService: AqiAPI by lazy {
        RetrofitService.aqiRetrofit.create(AqiAPI::class.java)
    }

    suspend fun getDailyAqiInfo(latitude: Double, longitude: Double): DataResult<DailyAqiWeatherDto> {
        try {
            val response = apiService.getDailyAqiInfo(latitude, longitude)
            if (response.isSuccessful) {
                val weatherDto = response.body()?.toDto() ?: DailyAqiWeatherDto()
                return DataResult.Success(weatherDto)
            }
            return DataResult.Error(Exception("Network Error"))
        } catch (e: Exception) {
            return DataResult.Error(e)
        }
    }
}