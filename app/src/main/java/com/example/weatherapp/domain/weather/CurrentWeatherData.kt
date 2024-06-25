package com.example.weatherapp.domain.weather

import java.time.LocalDateTime

data class CurrentWeatherData(
    val time: LocalDateTime,
    val temperature: Double,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double,
    val weatherType: WeatherType,
    val location: String
    )
