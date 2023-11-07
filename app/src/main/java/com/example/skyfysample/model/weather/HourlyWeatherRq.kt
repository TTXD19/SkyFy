package com.example.skyfysample.model.weather

data class HourlyWeatherRq(
    val latitude: Double,
    val longitude: Double,
    val forecastDays: String
)
