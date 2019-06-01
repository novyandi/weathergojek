package com.android.weathergojek.domain.repository

import com.android.weathergojek.domain.model.Weather
import com.android.weathergojek.domain.model.request.WeatherRequest
import io.reactivex.Observable

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
interface WeatherRepository {
    fun getForecastWeather(request: WeatherRequest): Observable<Weather>
}