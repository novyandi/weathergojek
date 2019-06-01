package com.android.weathergojek.data.datasource

import com.android.weathergojek.data.Constant
import com.android.weathergojek.domain.model.Weather
import com.android.weathergojek.domain.model.request.WeatherRequest
import io.reactivex.Observable

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
class WeatherLocalDataSource : WeatherDataSource {
    override fun getCurrentWeather(request: WeatherRequest): Observable<Weather> {
        throw IllegalArgumentException(Constant.NOT_IMPLEMENT_LOCAL_DATASOURCE)
    }

    override fun getForecastWeather(request: WeatherRequest): Observable<Weather> {
        throw IllegalArgumentException(Constant.NOT_IMPLEMENT_LOCAL_DATASOURCE)
    }
}
