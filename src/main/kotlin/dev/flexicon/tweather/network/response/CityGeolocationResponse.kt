package dev.flexicon.tweather.network.response

import kotlinx.serialization.Serializable

@Serializable
data class CityGeolocationResponse(
    val results: List<Result> = emptyList(),
) {

    @Serializable
    data class Result(
        val latitude: Double,
        val longitude: Double,
    )
}
