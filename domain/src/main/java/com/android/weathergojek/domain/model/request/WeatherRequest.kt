package com.android.weathergojek.domain.model.request

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
class WeatherRequest(
    val latitude: Double,
    val longitude: Double,
    val apiKey: String
) {
    companion object {
        const val API_KEY_FIELD = "key"
        const val QUERY_FIELD = "q"
    }

    fun toApiParameter(): HashMap<String, Any> {
        return HashMap<String, Any>().apply {
            put(API_KEY_FIELD, apiKey)
            put(QUERY_FIELD, "$latitude,$longitude")
        }
    }
}
