package com.example.weatherforecast

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class WeatherViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = WeatherRepository(application)

    private val _weather = MutableStateFlow<WeatherResponse?>(null)
    val weather: StateFlow<WeatherResponse?> = _weather

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun getWeather(city: String, apiKey: String) {

        viewModelScope.launch {

            _isLoading.value = true
            _error.value = null

            try {

                // Get fresh weather from API
                val result = repository.getWeather(
                    city = city,
                    apiKey = apiKey
                )

                _weather.value = result

            } catch (e: Exception) {

                if (e is IOException) {

                    // No internet → try local cache
                    val cachedWeather =
                        repository.getCachedWeather(city)

                    if (cachedWeather != null) {

                        _weather.value = cachedWeather

                        _error.value =
                            "No internet. Showing cached weather."

                    } else {

                        _error.value =
                            "No internet connection"
                    }

                } else if (e is HttpException) {

                    _error.value = when (e.code()) {

                        404 -> "City not found"

                        401 -> "Invalid or inactive API key"

                        else -> "Weather service error"
                    }

                } else {

                    _error.value =
                        "Unable to fetch weather"
                }
            }

            finally {

                _isLoading.value = false
            }
        }
    }
}