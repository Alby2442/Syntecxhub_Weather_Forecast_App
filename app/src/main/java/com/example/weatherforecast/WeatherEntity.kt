package com.example.weatherforecast

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherEntity(

    @PrimaryKey
    val city: String,

    val temperature: Double,
    val feelsLike: Double,
    val humidity: Int,
    val windSpeed: Double,

    val description: String,
    val icon: String,

    val sunrise: Long,
    val sunset: Long,

    val timestamp: Long
)
