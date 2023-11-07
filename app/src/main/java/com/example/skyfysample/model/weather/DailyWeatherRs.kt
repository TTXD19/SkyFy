package com.example.skyfysample.model.weather

import com.google.gson.annotations.SerializedName

data class DailyWeatherRs(
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val elevation: Double,
    val daily: Daily,
    @SerializedName("generationtime_ms") val generationTimeMs: Double,
    @SerializedName("utc_offset_seconds") val utcOffsetSeconds: Int,
    @SerializedName("timezone_abbreviation") val timezoneAbbreviation: String,
    @SerializedName("daily_units") val dailyUnits: DailyUnits
)

data class DailyUnits(
    val time: String,
    val sunset: String,
    @SerializedName("weathercode") val weatherCode: String,
    @SerializedName("temperature_2m_max") val temperature2mMax: String,
    @SerializedName("uv_index_max") val uvIndexMax: String
)

data class Daily(
    val time: List<String>,
    val sunset: List<String>,
    @SerializedName("weathercode") val weatherCode: List<Int>,
    @SerializedName("temperature_2m_max") val temperature2mMax: List<Double>,
    @SerializedName("uv_index_max") val uvIndexMax: List<Double>
)