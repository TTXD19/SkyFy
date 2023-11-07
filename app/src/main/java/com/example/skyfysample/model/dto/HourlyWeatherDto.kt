package com.example.skyfysample.model.dto

import com.example.skyfysample.model.weather.HourlyWeatherRs

data class HourlyWeatherDto(
    val location: String = "",
    val timezone: String = "",
    val hourlyForecasts: List<HourlyForecastDto> = emptyList()
)

data class HourlyForecastDto(
    val time: String,
    val temperature: Double,
    val weatherCode: Int,
)

fun HourlyWeatherRs.toDto(): HourlyWeatherDto {
    return HourlyWeatherDto(
        location = "${this.latitude}, ${this.longitude}",
        timezone = this.timeZone,
        hourlyForecasts = this.hourly.times.mapIndexed { index, date ->
            HourlyForecastDto(
                time = date,
                temperature = this.hourly.temperatures[index],
                weatherCode = this.hourly.weatherCodes[index],
            )
        }
    )
}