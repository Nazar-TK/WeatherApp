package com.example.weatherapp.data.repository

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import com.example.weatherapp.core.utils.Resource
import com.example.weatherapp.data.remote.WeatherApi
import com.example.weatherapp.domain.repository.WeatherRepository
import com.example.weatherapp.domain.weather.CurrentWeatherData
import com.example.weatherapp.domain.weather.WeatherStatusIcon
import okhttp3.ResponseBody
import java.io.InputStream
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
                apiKey = "fa3c9b3d2b1a427475069e4c439edf86"
            ).toCurrentWeatherData())
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Something went wrong.")
        }
    }

    override suspend fun getImage(imageId: String): Resource<ResponseBody> {
        return try {
            Resource.Success(data = api.fetchImage(iconCode = imageId))
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "Something went wrong.")
        }
    }
}