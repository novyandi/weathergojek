package com.android.weathergojek.data.mapper

import com.android.weathergojek.data.model.WeatherResponse
import com.android.weathergojek.domain.model.Temperature
import com.android.weathergojek.domain.model.Weather
import io.reactivex.functions.Function

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
class WeatherResponseMapper : Function<WeatherResponse, Weather> {
    override fun apply(response: WeatherResponse): Weather {
        return Weather(
            response.location.name,
            response.location.region,
            response.location.country,
            Temperature(response.currentWeather.tempC, response.currentWeather.tempF),
            ForecastResponseMapper().apply(response.forecastWeather)
        )
    }
}
