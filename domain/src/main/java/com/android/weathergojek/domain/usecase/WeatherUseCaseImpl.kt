package com.android.weathergojek.domain.usecase

import com.android.weathergojek.domain.model.Weather
import com.android.weathergojek.domain.model.request.WeatherRequest
import com.android.weathergojek.domain.repository.WeatherRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
class WeatherUseCaseImpl(private val weatherRepository: WeatherRepository) : WeatherUseCase {
    private var compositeDisposable: CompositeDisposable? = null

    override fun getForecastWeather(request: WeatherRequest): Observable<Weather> {
        return weatherRepository.getForecastWeather(request)
    }

    override fun addDisposable(disposable: Disposable) {
        if (compositeDisposable == null) compositeDisposable = CompositeDisposable()
        compositeDisposable?.add(disposable)
    }

    override fun clearDisposable() {
        compositeDisposable?.clear()
    }
}