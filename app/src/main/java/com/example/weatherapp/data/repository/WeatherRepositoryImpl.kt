package com.example.weatherapp.data.repository

import android.util.Log
import com.example.weatherapp.core.utils.Resource
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.weather.CurrentWeatherData
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherApi, private val client: OkHttpClient): WeatherRepository {
    override suspend fun getCurrentWeatherData(
        latitude: Double,
        longitude: Double
    ): Resource<CurrentWeatherData> {
        return try {
            Resource.Success(data = api.getWeatherData(
                latitude = latitude,
                longitude = longitude,
                apiKey = "fa3c9b3d2b1a427475069e4c439edf86"
            ).toCurrentWeatherData())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Something went wrong.")
        }
    }

    override suspend fun getImage(imageId: String): Resource<Response> {
        return try {
            val url = "https://openweathermap.org/img/wn/$imageId@2x.png"
            val request = Request.Builder().url(url).build()
            val response = client.newCall(request).execute()
            Resource.Success(data = response)
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("HERE!! getImage icon id",  e.message.toString())
            Resource.Error(e.message ?: "Something went wrong.")
        }
    }
}