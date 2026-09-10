package com.example.weatherforecast

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)

    @Query("SELECT * FROM weather WHERE city = :city LIMIT 1")
    suspend fun getWeather(city: String): WeatherEntity?

    @Query("DELETE FROM weather")
    suspend fun deleteAllWeather()
}

