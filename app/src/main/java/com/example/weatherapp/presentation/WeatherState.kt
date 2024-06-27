package com.example.weatherapp.presentation

import androidx.compose.ui.graphics.painter.Painter
import com.example.weatherapp.domain.weather.CurrentWeatherData

data class WeatherState(
    val weatherData: CurrentWeatherData? = null,
    val error: String? = null
)
