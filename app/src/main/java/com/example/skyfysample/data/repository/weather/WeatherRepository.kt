package com.example.skyfysample.data.repository.weather

import com.example.skyfysample.data.api.weather.WeatherAPI
import com.example.skyfysample.data.network.RetrofitService
import com.example.skyfysample.data.repository.DataResult
import com.example.skyfysample.model.weather.WeatherDto
import com.example.skyfysample.model.weather.toDto

class WeatherRepository {

    private val apiService: WeatherAPI by lazy {
        RetrofitService.retrofit.create(WeatherAPI::class.java)
    }

    suspend fun getWeatherInfo(latitude: Double, longitude: Double): DataResult<WeatherDto> {
        try {
            val weatherResponse = apiService.getWeatherInfoDaily(latitude, longitude)
            if (weatherResponse.isSuccessful) {
                val weatherDto = weatherResponse.body()?.toDto() ?: WeatherDto()
                return DataResult.Success(weatherDto)
            }
            return DataResult.Error(Exception("Network Error"))
        } catch (e: Exception) {
            return DataResult.Error(e)
        }
    }
}