package dev.flexicon.tweather.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastResponse(
    @SerialName("current_weather")
    val current: WeatherData
) {
    @Serializable
    data class WeatherData(
        @SerialName("weathercode")
        val weatherCode: Int,
        val temperature: Double
    )
}
