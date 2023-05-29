package dev.flexicon.tweather

import dev.flexicon.tweather.network.GeolocationApi
import dev.flexicon.tweather.network.IPApi
import dev.flexicon.tweather.network.WeatherApi
import dev.flexicon.tweather.network.buildHttpClient
import dev.flexicon.tweather.usecases.ForecastFetcher

fun main(args: Array<String>) {
    buildHttpClient().use { httpClient ->
        val ipApi = IPApi(httpClient)
        val geolocationApi = GeolocationApi(httpClient)
        val weatherApi = WeatherApi(httpClient)
        val forecastFetcher = ForecastFetcher(ipApi, geolocationApi, weatherApi)

        TWeather(forecastFetcher).main(args)
    }
}
