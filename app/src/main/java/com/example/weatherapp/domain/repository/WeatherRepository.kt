package com.example.weatherapp.domain.repository

import com.example.weatherapp.core.utils.Resource
import com.example.weatherapp.domain.weather.CurrentWeatherData

interface WeatherRepository {
    suspend fun getCurrentWeatherData(latitude: Double, longitude: Double): Resource<CurrentWeatherData>
}