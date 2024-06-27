package com.example.weatherapp.domain.weather

import java.time.LocalDateTime

data class CurrentWeatherData(
    val time: LocalDateTime,
    val temperature: Double,
    val pressure: Int,
    val humidity: Int,
    val windSpeed: Double,
    val location: String,
    val weatherStatus: String,
    val iconId: String
    )
