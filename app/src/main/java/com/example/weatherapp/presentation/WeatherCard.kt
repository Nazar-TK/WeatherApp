package com.example.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Composable
fun WeatherCard(
    state: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    state.weatherData?.let { data ->
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp),
            colors = CardDefaults.cardColors(backgroundColor)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = data.time.format(DateTimeFormatter.ofPattern("dd MMMM  HH:mm")),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.White,
                    fontSize = 24.sp,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = data.location,
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.White,
                    fontSize = 24.sp,
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = data.weatherIcon ?: painterResource(R.drawable.ic_sunny),
                    contentDescription = null,
                    modifier = Modifier.width(150.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${data.temperature.roundToInt()}°C",
                    fontSize = 50.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = data.weatherStatus,
                    fontSize = 24.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherIndicator(
                        value = data.pressure,
                        unit = "hpa",
                        icon = ImageVector.vectorResource(id = R.drawable.pressure),
                        iconTint = Color.White,
                    )
                    WeatherIndicator(
                        value = data.humidity,
                        unit = "%",
                        icon = ImageVector.vectorResource(id = R.drawable.humidity),
                        iconTint = Color.White,
                    )
                    WeatherIndicator(
                        value = data.windSpeed.roundToInt(),
                        unit = "m/s",
                        icon = ImageVector.vectorResource(id = R.drawable.windspeed),
                        iconTint = Color.White,
                    )
                }
            }
        }
    }
}

@Composable
fun WeatherIndicator(
    value: Int,
    unit: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    iconTint: Color = Color.White
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = "$value$unit",
            color = Color.White,
            fontSize = 20.sp
        )
    }
}