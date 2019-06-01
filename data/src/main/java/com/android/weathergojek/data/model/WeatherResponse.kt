package com.android.weathergojek.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
data class WeatherResponse(
    @SerializedName("location")
    val location: LocationResponse,
    @SerializedName("current")
    val currentWeather: CurrentWeatherResponse
)
