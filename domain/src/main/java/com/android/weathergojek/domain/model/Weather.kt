package com.android.weathergojek.domain.model

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
data class Weather(
    val location: String,
    val region: String,
    val country: String,
    val temperature: Temperature,
    val forecastDays: List<Forecast>? = null
)
