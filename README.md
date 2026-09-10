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
* Responsive UI for different screen sizes and orientations

## 🛠️ Technologies Used

* **Kotlin**
* **XML**
* **Android Studio**
* **OpenWeatherMap API**
* **Retrofit**
* **Gson**
* **Kotlin Coroutines**
* **ViewModel**
* **Coil**
* **Room Database**

## 🏗️ Architecture

The application follows a simple MVVM-style architecture:

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

The **Retrofit API** is used to retrieve current weather information from OpenWeatherMap.

The **Room Database** is used to store previously retrieved weather information. If the device is offline, the application can display cached weather data for a previously searched city.

## 🔐 API Key Security

The OpenWeatherMap API key is stored locally using `local.properties` and is **not included in the GitHub repository**.

The API key is accessed through `BuildConfig` during the application build.

## 📸 Screenshots

### Main Screen

![Main Screen](./screenshots/01_main_screen.jpeg)

### Weather Result

![Weather Result](./screenshots/02_weather_result.jpeg)

### City Not Found

![City Not Found](./screenshots/03_city_not_found.jpeg)

### Cached Weather

![Cached Weather](./screenshots/04_cached_weather.jpeg)

## 📦 APK Download

The latest APK is available through the GitHub Release.

**[Download Weather Forecast App v1.0.0](../../releases/tag/v1.0.0)**

## 🎓 Internship

This project was developed as part of my **Android App Development Internship at Syntecxhub**.

The project helped me improve my practical understanding of Android development, API integration, MVVM architecture, local data storage, error handling, and Kotlin programming.

## 👨‍💻 Developer

**Albin Saji**

BCA – Computer Applications

## 📄 Project Information

**Project:** Weather Forecast App
**Version:** 1.0.0
**Platform:** Android
**Language:** Kotlin
**UI:** XML
**Internship:** Syntecxhub Android App Development Internship
