package dev.flexicon.tweather.network

import dev.flexicon.tweather.network.response.ForecastResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import io.ktor.util.StringValues

class WeatherApi(private val httpClient: HttpClient) {
    suspend fun getForecast(latitude: Double, longitude: Double): ForecastResponse =
        httpClient.get("${BASE_URL}/forecast") {
            url {
                parameters.appendAll(StringValues.build {
                    append("current_weather", "true")
                    append("latitude", latitude.toString())
                    append("longitude", longitude.toString())
                })
            }
        }.also {
            require(it.status.isSuccess()) { "Bad Weather API response: ${it.bodyAsText()}" }
        }.body()

    companion object {
        private const val BASE_URL = "https://api.open-meteo.com/v1"
    }
}
