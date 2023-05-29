package dev.flexicon.tweather.network

import dev.flexicon.tweather.network.response.CityGeolocationResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess

class GeolocationApi(private val httpClient: HttpClient) {
    suspend fun getCityGeolocation(city: String): CityGeolocationResponse =
        httpClient.get("${BASE_URL}/search") {
            url {
                parameters.append("name", city)
            }
        }.also {
            require(it.status.isSuccess()) { "Bad Geolocation API response: ${it.bodyAsText()}" }
        }.body()

    companion object {
        private const val BASE_URL = "https://geocoding-api.open-meteo.com/v1"
    }
}