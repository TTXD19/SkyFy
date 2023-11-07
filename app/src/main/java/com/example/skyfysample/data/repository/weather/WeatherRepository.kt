package com.example.skyfysample.data.repository.weather

import com.example.skyfysample.data.api.weather.WeatherAPI
import com.example.skyfysample.data.network.RetrofitService
import com.example.skyfysample.data.repository.DataResult
import com.example.skyfysample.model.dto.HourlyWeatherDto
import com.example.skyfysample.model.dto.DailyWeatherDto
import com.example.skyfysample.model.dto.toDto

class WeatherRepository {

    private val apiService: WeatherAPI by lazy {
        RetrofitService.retrofit.create(WeatherAPI::class.java)
    }

    suspend fun getDailyWeatherInfo(latitude: Double, longitude: Double): DataResult<DailyWeatherDto> {
        try {
            val weatherResponse = apiService.getWeatherInfoDaily(latitude, longitude)
            if (weatherResponse.isSuccessful) {
                val weatherDto = weatherResponse.body()?.toDto() ?: DailyWeatherDto()
                return DataResult.Success(weatherDto)
            }
            return DataResult.Error(Exception("Network Error"))
        } catch (e: Exception) {
            return DataResult.Error(e)
        }
    }

    suspend fun getHourlyWeatherInfo(
        latitude: Double,
        longitude: Double,
        forecastDays: String
    ): DataResult<HourlyWeatherDto> {
        try {
            val response = apiService.getWeatherInfoHourly(latitude, longitude, forecastDays)
            if (response.isSuccessful) {
                val weatherDto = response.body()?.toDto() ?: HourlyWeatherDto()
                return DataResult.Success(weatherDto)
            }
            return DataResult.Error(Exception("Network Error"))
        } catch (e: Exception) {
            return DataResult.Error(e)
        }
    }
}