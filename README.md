# Weather Forecast App 🌤️

A simple and user-friendly Android Weather Forecast application developed as part of my **Android App Development Internship at Syntecxhub**.

The app allows users to search for the current weather of any city and displays important weather information in a clean and responsive interface.

## 📱 Features

* Search weather by city name
* Current temperature in Celsius
* Feels-like temperature
* Weather condition and description
* Weather condition icon
* Humidity information
* Wind speed
* Sunrise and sunset time
* Loading indicator
* City not found error handling
* Invalid API key error handling
* Network error handling
* Offline weather data using Room Database cache
* Displays previously cached weather when there is no internet connection
* Responsive UI

## 🛠️ Technologies Used

* Kotlin
* XML
* Android Studio
* OpenWeatherMap API
* Retrofit
* Gson
* Kotlin Coroutines
* ViewModel
* Coil
* Room Database

## 🏗️ Architecture

```text
MainActivity
     ↓
ViewModel
     ↓
Repository
   ↙   ↘
Retrofit  Room Database
   ↓        ↓
OpenWeatherMap API
```

The application uses Retrofit to retrieve weather information from the OpenWeatherMap API.

Room Database is used to cache previously retrieved weather information and display it when the device is offline.

## 🔐 API Key Security

The OpenWeatherMap API key is stored in `local.properties` and is not included in this GitHub repository.

The API key is accessed through `BuildConfig` during the application build.

## 📸 Screenshots

### Main Screen

[View Main Screen](./screenshots/01_main_screen)

### Weather Result

[View Weather Result](./screenshots/02_weather_result)

### City Not Found

[View City Not Found](./screenshots/03_city_not_found)

### Cached Weather

[View Cached Weather](./screenshots/04_cached_weather)

## 📦 APK Download

Download the latest APK from the GitHub Release:

**[Download Weather Forecast App v1.0.0](https://github.com/Alby2442/Syntecxhub_Weather_Forecast_App/releases/download/untagged-eaeb42d8e04e37d201eb)**

## 🎓 Internship

This project was developed as part of my **Android App Development Internship at Syntecxhub**.

This project helped me improve my practical knowledge of Android development, Kotlin programming, API integration, MVVM architecture, local data storage, error handling, and offline data caching.

## 👨‍💻 Developer

**Albin Saji**

BCA – Computer Applications

## 📄 Project Information

| Information | Details                                       |
| ----------- | --------------------------------------------- |
| Project     | Weather Forecast App                          |
| Version     | 1.0.0                                         |
| Platform    | Android                                       |
| Language    | Kotlin                                        |
| UI          | XML                                           |
| API         | OpenWeatherMap                                |
| Internship  | Syntecxhub Android App Development Internship |
