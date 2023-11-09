package com.example.skyfysample.model.dto

import com.example.skyfysample.model.weather.DailyAqiWeatherRs
import com.example.skyfysample.model.weather.WeatherAqi
import com.example.skyfysample.model.weather.WeatherAqiUnits

data class DailyAqiWeatherDto(
    val latitude: Double = 1.0,
    val longitude: Double = 1.0,
    val generationTimeMs: Double = 1.0,
    val utcOffsetSeconds: Int = 0,
    val timezone: String = "",
    val timezoneAbbreviation: String = "",
    val elevation: Double = 1.0,
    val currentUnits: WeatherAqiUnitsDTO = WeatherAqiUnitsDTO(),
    val current: WeatherAqiDTO = WeatherAqiDTO()
)

data class WeatherAqiUnitsDTO(
    val time: String = "",
    val interval: String = "",
    val europeanAqi: String = "",
    val pm25: String = "",
    val uvIndex: String = ""
)

data class WeatherAqiDTO(
    val time: String = "",
    val interval: Int = 0,
    val europeanAqi: Int = 0,
    val pm25: Double = 1.0,
    val uvIndex: Double = 1.0
)

fun DailyAqiWeatherRs.toDto(): DailyAqiWeatherDto {
    return DailyAqiWeatherDto(
        latitude = this.latitude,
        longitude = this.longitude,
        generationTimeMs = this.generationTimeMs,
        utcOffsetSeconds = this.utcOffsetSeconds.toInt(), // casting Long to Int
        timezone = this.timezone,
        timezoneAbbreviation = this.timezoneAbbreviation,
        elevation = this.elevation,
        currentUnits = this.units.toDto(),
        current = this.weatherAqi.toDto()
    )
}

fun WeatherAqiUnits.toDto(): WeatherAqiUnitsDTO {
    return WeatherAqiUnitsDTO(
        time = this.time,
        interval = this.interval,
        europeanAqi = this.europeanAqi,
        pm25 = this.pm25,
        uvIndex = this.uv_index
    )
}

fun WeatherAqi.toDto(): WeatherAqiDTO {
    return WeatherAqiDTO(
        time = this.time,
        interval = this.interval,
        europeanAqi = this.europeanAqi,
        pm25 = this.pm25,
        uvIndex = this.uvIndex
    )
}
