package com.example.skyfysample.model.weather

import com.google.gson.annotations.SerializedName

data class DailyAqiWeatherRs(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val elevation: Double,
    @SerializedName("generationtime_ms") val generationTimeMs: Double,
    @SerializedName("utc_offset_seconds") val utcOffsetSeconds: Long,
    @SerializedName("timezone_abbreviation") val timezoneAbbreviation: String,
    @SerializedName("current") val weatherAqi: WeatherAqi,
    @SerializedName("current_units") val units: WeatherAqiUnits
)

data class WeatherAqiUnits(
    val time: String,
    val interval: String,
    val uv_index: String,
    @SerializedName("european_aqi") val europeanAqi: String,
    @SerializedName("pm2_5") val pm25: String
)

data class WeatherAqi(
    val time: String,
    val interval: Int,
    @SerializedName("european_aqi") val europeanAqi: Int,
    @SerializedName("pm2_5") val pm25: Double,
    @SerializedName("uv_index") val uvIndex: Double
)
