package com.example.weatherapp.domain.repository

import com.example.weatherapp.core.utils.Resource
import com.example.weatherapp.domain.weather.CurrentWeatherData
import okhttp3.Response
import okhttp3.ResponseBody

interface WeatherRepository {
    suspend fun getCurrentWeatherData(latitude: Double, longitude: Double): Resource<CurrentWeatherData>

    suspend fun getImage(imageId: String): Resource<Response>
}