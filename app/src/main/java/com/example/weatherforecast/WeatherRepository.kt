package com.example.weatherforecast

import android.content.Context

class WeatherRepository(context: Context) {

    private val weatherApi = RetrofitClient.weatherApi

    private val weatherDao =
        WeatherDatabase.getDatabase(context).weatherDao()

    suspend fun getWeather(
        city: String,
        apiKey: String
    ): WeatherResponse {

        // Get fresh weather from API
        val response = weatherApi.getCurrentWeather(
            city = city,
            apiKey = apiKey
        )

        // Save the successful result to local cache
        val weatherEntity = WeatherEntity(
            city = response.name,
            temperature = response.main.temp,
            feelsLike = response.main.feels_like,
            humidity = response.main.humidity,
            windSpeed = response.wind.speed,
            description = response.weather[0].description,
            icon = response.weather[0].icon,
            sunrise = response.sys.sunrise,
            sunset = response.sys.sunset,
            timestamp = System.currentTimeMillis()
        )

        weatherDao.insertWeather(weatherEntity)

        return response
    }

    // Get weather from local cache
    suspend fun getCachedWeather(city: String): WeatherResponse? {

        val cached = weatherDao.getWeather(city) ?: return null

        return WeatherResponse(
            name = cached.city,

            main = MainWeather(
                temp = cached.temperature,
                feels_like = cached.feelsLike,
                humidity = cached.humidity
            ),

            wind = Wind(
                speed = cached.windSpeed
            ),

            weather = listOf(
                Weather(
                    main = cached.description,
                    description = cached.description,
                    icon = cached.icon
                )
            ),

            sys = SystemWeather(
                sunrise = cached.sunrise,
                sunset = cached.sunset
            )
        )
    }
}

