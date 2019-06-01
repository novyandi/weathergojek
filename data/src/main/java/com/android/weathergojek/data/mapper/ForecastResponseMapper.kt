package com.android.weathergojek.data.mapper

import com.android.weathergojek.data.model.ForecastResponse
import com.android.weathergojek.domain.model.Forecast
import com.android.weathergojek.domain.model.Temperature
import io.reactivex.functions.Function

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
class ForecastResponseMapper : Function<ForecastResponse, List<Forecast>> {
    override fun apply(response: ForecastResponse): List<Forecast> {
        return mutableListOf<Forecast>().apply {
            response.forecastDays.forEach {
                add(Forecast(it.date, Temperature(it.day.avgtempC, it.day.avgtempF)))
            }
        }
    }
}
