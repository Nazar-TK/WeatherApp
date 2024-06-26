package com.example.weatherapp.data.remote

import com.example.weatherapp.data.remote.dto.CurrentWeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather?units=metric")
    suspend fun getWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
        ) : CurrentWeatherDto


    companion object {
        const val BASE_URL = "https://api.openweathermap.org/"
    }
}