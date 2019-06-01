package com.android.weathergojek.data.repository

import com.android.weathergojek.data.datasource.WeatherDataSource
import com.android.weathergojek.domain.model.Weather
import com.android.weathergojek.domain.model.request.WeatherRequest
import com.android.weathergojek.domain.repository.WeatherRepository
import io.reactivex.Observable

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
class WeatherRepositoryImpl(
    private val localDataSource: WeatherDataSource,
    private val remoteDataSource: WeatherDataSource
) : WeatherRepository {

    override fun getForecastWeather(request: WeatherRequest): Observable<Weather> {
        return remoteDataSource.getForecastWeather(request)
    }
}
