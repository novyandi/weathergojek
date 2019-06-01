package com.android.weathergojek.data.datasource

import com.android.weathergojek.data.mapper.WeatherResponseMapper
import com.android.weathergojek.data.remote.ApiService
import com.android.weathergojek.domain.model.Weather
import com.android.weathergojek.domain.model.request.WeatherRequest
import io.reactivex.Observable

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
class WeatherRemoteDataSource(private val apiService: ApiService) : WeatherDataSource {
    override fun getCurrentWeather(request: WeatherRequest): Observable<Weather> {
        return apiService.getCurrentWeather(request.toApiParameter()).map(WeatherResponseMapper())
    }

    override fun getForecastWeather(request: WeatherRequest): Observable<Weather> {
        return apiService.getForecastWeather(request.toApiParameter()).map(WeatherResponseMapper())
    }
}
