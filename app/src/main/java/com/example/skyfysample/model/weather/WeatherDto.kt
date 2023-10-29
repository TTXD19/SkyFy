package com.example.skyfysample.model.weather

data class WeatherDto(
    val location: String = "",
    val timezone: String = "",
    val dailyForecasts: List<DailyForecast> = emptyList(),
    val hourlyForecasts: List<HourlyForecast> = emptyList()
)

data class DailyForecast(
    val date: String,
    val weatherCode: Int,
    val maxTemperature: Double,
    val time: String,
    val sunsetTime: String,
    val uvIndexMax: Double
)

data class HourlyForecast(
    val time: String,
    val temperature: Double,
    val weatherCode: Int,
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
                time = this.daily.time[index],
                sunsetTime = this.daily.sunset[index],
                uvIndexMax = this.daily.uvIndexMax[index]
            )
        },
        hourlyForecasts = this.hourly.time.mapIndexed { index, time ->   // Map hourly data
            HourlyForecast(
                time = time,
                temperature = this.hourly.temperature2m[index],
                weatherCode = this.hourly.weatherCode[index]
            )
        }
    )
}