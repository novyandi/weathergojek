package com.android.weathergojek.domain.usecase

import com.android.weathergojek.domain.model.Weather
import com.android.weathergojek.domain.model.request.WeatherRequest
import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
interface WeatherUseCase {
    fun getForecastWeather(request: WeatherRequest): Observable<Weather>
    fun addDisposable(disposable: Disposable)
    fun clearDisposable()
}
