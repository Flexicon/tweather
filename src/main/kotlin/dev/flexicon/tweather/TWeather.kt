package dev.flexicon.tweather

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.multiple
import dev.flexicon.tweather.usecases.ForecastFetcher
import kotlinx.coroutines.runBlocking

class TWeather(private val forecastFetcher: ForecastFetcher) : CliktCommand() {
    private val cityArg by argument().multiple(required = false)

    private val cityName by lazy {
        cityArg.joinToString(separator = " ").ifBlank { null }
    }

    override fun run() = runBlocking {
        echo(forecastFetcher.fetchForecast(cityName))
    }
}
