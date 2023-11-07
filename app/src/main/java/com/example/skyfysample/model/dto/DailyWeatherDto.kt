package com.example.skyfysample.model.dto

import com.example.skyfysample.model.weather.DailyWeatherRs

data class DailyWeatherDto(
    val location: String = "",
    val timezone: String = "",
    val dailyForecastDtos: List<DailyForecastDto> = emptyList(),
)

data class DailyForecastDto(
    val date: String,
    val weatherCode: Int,
    val maxTemperature: Double,
    val time: String,
    val sunsetTime: String,
    val uvIndexMax: Double
)

fun DailyWeatherRs.toDto(): DailyWeatherDto {
    return DailyWeatherDto(
        location = "${this.latitude}, ${this.longitude}",
        timezone = this.timezone,
        dailyForecastDtos = this.daily.time.mapIndexed { index, date ->
            DailyForecastDto(
                date = date,
                weatherCode = this.daily.weatherCode[index],
                maxTemperature = this.daily.temperature2mMax[index],
                time = this.daily.time[index],
                sunsetTime = this.daily.sunset[index],
                uvIndexMax = this.daily.uvIndexMax[index]
            )
        }
    )
}