package com.example.skyfysample.model.weather

data class WeatherDto(
    val location: String = "",
    val timezone: String = "",
    val dailyForecasts: List<DailyForecast> = emptyList()
)

data class DailyForecast(
    val date: String,
    val weatherCode: Int,
    val maxTemperature: Double,
    val sunsetTime: String,
    val uvIndexMax: Double
)

fun WeatherResponse.toDto(): WeatherDto {
    return WeatherDto(
        location = "${this.latitude}, ${this.longitude}",
        timezone = this.timezone,
        dailyForecasts = this.daily.time.mapIndexed { index, date ->
            DailyForecast(
                date = date,
                weatherCode = this.daily.weatherCode[index],
                maxTemperature = this.daily.temperature2mMax[index],
                sunsetTime = this.daily.sunset[index],
                uvIndexMax = this.daily.uvIndexMax[index]
            )
        }
    )
}