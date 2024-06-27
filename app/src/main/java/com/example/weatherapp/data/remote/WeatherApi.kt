package com.example.weatherapp.data.remote

import com.example.weatherapp.data.remote.dto.CurrentWeatherDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface WeatherApi {

    @GET("data/2.5/weather?units=metric")
    suspend fun getWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
        ) : CurrentWeatherDto

    //@GET("img/wn/{iconCode}@2x.png")
    //https://openweathermap.org/img/wn/10d@2x.png
    //suspend fun fetchImage(@Path("iconCode") iconCode: String): ResponseBody
    @GET
    suspend fun fetchImage(@Url url: String): ResponseBody

    companion object {
        const val BASE_URL = "https://api.openweathermap.org/"
    }
}