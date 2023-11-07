package com.example.skyfysample.model.weather

import com.google.gson.annotations.SerializedName


data class HourlyWeatherRs(
    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double,
    @SerializedName("generationtime_ms") val generationTimeMs: Double,
    @SerializedName("utc_offset_seconds") val utcOffsetSeconds: Int,
    @SerializedName("timezone") val timeZone: String,
    @SerializedName("timezone_abbreviation") val timeZoneAbbreviation: String,
    @SerializedName("elevation") val elevation: Double,
    @SerializedName("hourly_units") val hourlyUnits: HourlyUnits,
    @SerializedName("hourly") val hourly: HourlyData
)

data class HourlyUnits(
    @SerializedName("time") val timeFormat: String,
    @SerializedName("temperature_2m") val temperatureUnit: String,
    @SerializedName("weathercode") val weatherCodeFormat: String
)

data class HourlyData(
    @SerializedName("time") val times: List<String>,
    @SerializedName("temperature_2m") val temperatures: List<Double>,
    @SerializedName("weathercode") val weatherCodes: List<Int>
)