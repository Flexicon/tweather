package dev.flexicon.tweather.usecases

import dev.flexicon.tweather.domain.Weather
import dev.flexicon.tweather.network.IPApi
import dev.flexicon.tweather.network.WeatherApi
import dev.flexicon.tweather.network.response.ForecastResponse

class ForecastFetcher(
    private val ipApi: IPApi,
    private val weatherApi: WeatherApi,
) {
    suspend fun fetchForecast(): String = "It is ${fetchCurrentWeather()} today."

    private suspend fun fetchCurrentWeather(): Weather {
        val ipData = ipApi.getIPData()
        val forecast = weatherApi.getForecast(ipData.latitude, ipData.longitude)

        return forecast.toWeather()
    }

    private fun ForecastResponse.toWeather() =
        Weather(current.weatherCode, current.temperature)
}
