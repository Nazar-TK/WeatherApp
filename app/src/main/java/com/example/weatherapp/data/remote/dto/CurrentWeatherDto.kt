package com.example.weatherapp.data.remote.dto


import com.example.weatherapp.domain.weather.CurrentWeatherData
import com.example.weatherapp.domain.weather.WeatherType
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Locale

data class CurrentWeatherDto(
    val dt: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val weather: List<Weather>,
    val wind: Wind
) {
    fun toCurrentWeatherData(): CurrentWeatherData {

        // Convert the Unix timestamp to an Instant
        val instant = Instant.ofEpochSecond(dt.toLong())
        // Convert the Instant to a LocalDateTime in UTC
        val dateTimeUtc = LocalDateTime.ofInstant(instant, ZoneOffset.UTC)
        val dateTimeWithTimezone = dateTimeUtc.plusSeconds(timezone.toLong())
        // Get country name from country code
        val countryName = Locale("", sys.country).displayCountry

        return CurrentWeatherData(
            time = dateTimeWithTimezone,
            temperature = main.temp,
            pressure = main.pressure,
            humidity = main.humidity,
            windSpeed = wind.speed,
            weatherType = WeatherType(weather[0].description, weather[0].icon),
            location = "${name}, $countryName"
        )
    }
}