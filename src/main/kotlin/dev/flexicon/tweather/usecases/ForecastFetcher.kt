package dev.flexicon.tweather.usecases

import dev.flexicon.tweather.domain.Coordinates
import dev.flexicon.tweather.domain.Weather
import dev.flexicon.tweather.network.GeolocationApi
import dev.flexicon.tweather.network.IPApi
import dev.flexicon.tweather.network.WeatherApi
import dev.flexicon.tweather.network.response.CityGeolocationResponse
import dev.flexicon.tweather.network.response.ForecastResponse
import dev.flexicon.tweather.network.response.IPDataResponse

class ForecastFetcher(
    private val ipApi: IPApi,
    private val geolocationApi: GeolocationApi,
    private val weatherApi: WeatherApi,
) {
    suspend fun fetchForecast(city: String?): String {
        val (locatedCity, location) = fetchLocation(city)
        val forecast = weatherApi.getForecast(location.latitude, location.longitude)

        return buildForecastText(locatedCity, forecast.toWeather())
    }

    private fun buildForecastText(city: String?, weather: Weather) =
        "The weather${city?.let { " in $city" }.orEmpty()} is $weather today."

    private suspend fun fetchLocation(city: String?): Pair<String?, Coordinates> = city
        ?.let { getCoordinatesForCity(it) }
        ?.let { city to it }
        ?: (null to getLocalCoordinates())

    private fun ForecastResponse.toWeather() =
        Weather(current.weatherCode, current.temperature)

    private suspend fun getCoordinatesForCity(city: String) =
        geolocationApi.getCityGeolocation(city).toCoordinates()

    private suspend fun getLocalCoordinates() =
        ipApi.getIPData().toCoordinates()

    private fun IPDataResponse.toCoordinates() =
        Coordinates(latitude, longitude)

    private fun CityGeolocationResponse.toCoordinates() =
        results.firstOrNull()?.let {
            Coordinates(it.latitude, it.longitude)
        }
}
