package com.example.weatherapp.data.repository

import com.example.weatherapp.core.utils.Resource
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.weather.CurrentWeatherData
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherApi): WeatherRepository {
    override suspend fun getCurrentWeatherData(
        latitude: Double,
        longitude: Double
    ): Resource<CurrentWeatherData> {
        return try {
            Resource.Success(data = api.getWeatherData(
                latitude = latitude,
                longitude = longitude,
                apiKey = "aaaaaaaaaaaaaaaaaaa"
            ).toCurrentWeatherData())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Something went wrong.")
        }
    }
}