package dev.flexicon.tweather.network

import dev.flexicon.tweather.network.response.IPDataResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess

class IPApi(private val httpClient: HttpClient) {
    suspend fun getIPData(): IPDataResponse =
        httpClient.get(IP_URL).also {
            require(it.status.isSuccess()) { "Bad IP API response: ${it.bodyAsText()}" }
        }.body()

    companion object {
        private const val IP_URL = "https://tools.nerfthis.xyz/ip.json"
    }
}
