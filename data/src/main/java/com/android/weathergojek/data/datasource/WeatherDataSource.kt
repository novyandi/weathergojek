package com.android.weathergojek.data.datasource

import com.android.weathergojek.domain.model.Weather
import com.android.weathergojek.domain.model.request.WeatherRequest
import io.reactivex.Observable

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
interface WeatherDataSource {
    fun getCurrentWeather(request: WeatherRequest): Observable<Weather>
    fun getForecastWeather(request: WeatherRequest): Observable<Weather>
}