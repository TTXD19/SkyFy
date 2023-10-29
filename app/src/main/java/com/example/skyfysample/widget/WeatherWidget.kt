package com.example.skyfysample.widget

object WeatherWidget {
    fun getWeatherType(weatherCode: Int): WeatherType {
        return when (weatherCode) {
            0 -> WeatherType.SUNNY
            in 1..3 -> WeatherType.CLOUDY
            45, 48 -> WeatherType.CLOUDY // Fog and depositing rime fog can be treated as CLOUDY
            in 51..55, 56, 57, in 61..65, 66, 67, in 80..82 -> WeatherType.RAINY
            71, 73, 75, 77, 85, 86 -> WeatherType.FREEZING
            95, 96, 99 -> WeatherType.THUNDER
            else -> throw IllegalArgumentException("Invalid weather code: $weatherCode")
        }
    }
}

enum class WeatherType {
    SUNNY, CLOUDY, FREEZING, RAINY, THUNDER
}