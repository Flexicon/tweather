package dev.flexicon.tweather

import com.github.ajalt.clikt.core.CliktCommand
import dev.flexicon.tweather.usecases.ForecastFetcher
import kotlinx.coroutines.runBlocking

// TODO: implement passing in city override argument for weather data
class TWeather(private val forecastFetcher: ForecastFetcher) : CliktCommand() {
    override fun run() = runBlocking {
        echo(forecastFetcher.fetchForecast())
    }
}
