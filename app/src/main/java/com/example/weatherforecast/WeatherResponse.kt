
package com.example.weatherforecast

data class WeatherResponse(
    val name: String,
    val main: MainWeather,
    val wind: Wind,
    val weather: List<Weather>,
    val sys: SystemWeather
)

data class MainWeather(
    val temp: Double,
    val feels_like: Double,
    val humidity: Int
)

data class Wind(
    val speed: Double
)

data class Weather(
    val main: String,
    val description: String,
    val icon: String
)

data class SystemWeather(
    val sunrise: Long,
    val sunset: Long
)

