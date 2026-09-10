package com.example.weatherforecast

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import coil3.load
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    private lateinit var etCity: EditText
    private lateinit var btnSearch: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var tvCity: TextView
    private lateinit var ivWeatherIcon: ImageView
    private lateinit var tvTemperature: TextView
    private lateinit var tvFeelsLike: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvWind: TextView
    private lateinit var tvSunrise: TextView
    private lateinit var tvSunset: TextView
    private lateinit var tvError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->

            val systemBars =
                insets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )

            insets
        }

        initializeViews()
        setupSearchButton()
        observeWeather()
    }

    private fun initializeViews() {

        etCity = findViewById(R.id.etCity)
        btnSearch = findViewById(R.id.btnSearch)
        progressBar = findViewById(R.id.progressBar)

        tvCity = findViewById(R.id.tvCity)
        ivWeatherIcon = findViewById(R.id.ivWeatherIcon)
        tvTemperature = findViewById(R.id.tvTemperature)
        tvFeelsLike = findViewById(R.id.tvFeelsLike)
        tvDescription = findViewById(R.id.tvDescription)
        tvHumidity = findViewById(R.id.tvHumidity)
        tvWind = findViewById(R.id.tvWind)
        tvSunrise = findViewById(R.id.tvSunrise)
        tvSunset = findViewById(R.id.tvSunset)
        tvError = findViewById(R.id.tvError)
    }

    private fun setupSearchButton() {

        btnSearch.setOnClickListener {

            val city = etCity.text.toString().trim()

            if (city.isEmpty()) {

                etCity.error = "Please enter a city name"

                return@setOnClickListener
            }

            viewModel.getWeather(
                city = city,
                apiKey = BuildConfig.WEATHER_API_KEY
            )
        }
    }

    private fun observeWeather() {

        lifecycleScope.launch {

            viewModel.weather.collect { weather ->

                if (weather != null) {

                    tvCity.text = weather.name

                    // Temperature
                    tvTemperature.text =
                        "${weather.main.temp.toInt()}°C"

                    // Feels Like
                    tvFeelsLike.text =
                        "Feels like ${weather.main.feels_like.toInt()}°C"

                    // Weather description
                    tvDescription.text =
                        weather.weather[0].description.replaceFirstChar {
                            it.uppercase()
                        }

                    // Humidity
                    tvHumidity.text =
                        "Humidity: ${weather.main.humidity}%"

                    // Wind
                    tvWind.text =
                        "Wind Speed: ${weather.wind.speed} m/s"

                    // Sunrise
                    tvSunrise.text =
                        "Sunrise: ${formatTime(weather.sys.sunrise)}"

                    // Sunset
                    tvSunset.text =
                        "Sunset: ${formatTime(weather.sys.sunset)}"

                    // Weather icon
                    val iconCode = weather.weather[0].icon

                    val iconUrl =
                        "https://openweathermap.org/img/wn/${iconCode}@2x.png"

                    ivWeatherIcon.load(iconUrl)

                    ivWeatherIcon.visibility = View.VISIBLE

                    tvError.text = ""
                }
            }
        }

        lifecycleScope.launch {

            viewModel.isLoading.collect { loading ->

                progressBar.visibility =
                    if (loading) View.VISIBLE else View.GONE

                btnSearch.isEnabled = !loading
            }
        }

        lifecycleScope.launch {

            viewModel.error.collect { error ->

                if (error != null) {

                    tvError.text = error

                    ivWeatherIcon.visibility = View.GONE

                    Toast.makeText(
                        this@MainActivity,
                        error,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun formatTime(timestamp: Long): String {

        val date = Date(timestamp * 1000)

        val formatter =
            SimpleDateFormat("hh:mm a", Locale.getDefault())

        return formatter.format(date)
    }
}