package com.example.weatherapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.core.utils.Resource
import com.example.weatherapp.domain.location.LocationTracker
import com.example.weatherapp.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationTracker
): ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set


    fun loadWeatherInfo() {
        viewModelScope.launch {
            state = state.copy(
                error = null
            )
            locationTracker.getCurrentLocation()?.let { location ->
                when(val result = repository.getCurrentWeatherData(location.latitude, location.longitude)) {
                    is Resource.Success -> {
                        state = state.copy(
                            weatherData = result.data,
                            error = null
                        )
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

    fun loadImage() {
        viewModelScope.launch {
            when (val result =
                repository.getImage(state.weatherData.iconId)) {
                is Resource.Success -> {
                    state = state.copy(
                        weatherData = result.data,
                        error = null
                    )
                }

                is Resource.Error -> {
                    state = state.copy(
                        weatherData = null,
                        error = result.message
                    )
                }
            }
        }
    }
}