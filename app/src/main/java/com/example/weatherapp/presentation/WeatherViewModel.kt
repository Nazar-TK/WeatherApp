package com.example.weatherapp.presentation

import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.core.utils.Resource
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.InputStream
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when (val result =
                    repository.getCurrentWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherData = result.data,
                            error = null
                        )
                        Log.d("HERE!!", state.weatherData.toString())
                        loadImage()
                    }

                    is Resource.Error -> {
                        state = state.copy(
                            weatherData = null,
                            error = result.message
                        )
                    }
                }
            } ?: kotlin.run {
                state = state.copy(
                    error = "Couldn't retrieve the location. Please, grant permissions and enable GPS."
                )
            }
        }
    }


    private fun loadImage() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("HERE!! icon id", state.weatherData!!.iconId)
            when (val result =
                state.weatherData?.let { repository.getImage(state.weatherData!!.iconId) }) {
                is Resource.Success -> {
                    if (result.data != null) {

                        val inputStream: InputStream = result.data.body?.byteStream() ?: return@launch
                        val bitmap = BitmapFactory.decodeStream(inputStream)
                        val imageBitmap = bitmap.asImageBitmap()
                        state = state.copy(
                            weatherData = state.weatherData?.copy(
                                weatherIcon = BitmapPainter(
                                    imageBitmap
                                )
                            )
                        )
                    }
                }
                is Resource.Error -> {
                }
                else -> {}
            }
        }
    }
}