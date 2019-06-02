package com.android.weathergojek.screen.weather.viewModel.impl

import android.databinding.ObservableInt
import android.view.View
import com.android.weathergojek.BuildConfig
import com.android.weathergojek.R
import com.android.weathergojek.domain.model.Weather
import com.android.weathergojek.domain.model.request.WeatherRequest
import com.android.weathergojek.domain.usecase.WeatherUseCase
import com.android.weathergojek.screen.base.BaseViewModel
import com.android.weathergojek.screen.binding.ObservableText
import com.android.weathergojek.screen.weather.viewModel.WeatherViewModel
import io.reactivex.Completable
import javax.inject.Inject

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
class WeatherViewModelImpl @Inject constructor() : BaseViewModel<WeatherViewModelImpl>(), WeatherViewModel {
    @Inject
    lateinit var weatherUseCase: WeatherUseCase

    override val loadingVisibility: ObservableInt = ObservableInt(View.VISIBLE)
    override val errorViewVisibility: ObservableInt = ObservableInt(View.GONE)
    override val weatherViewVisibility: ObservableInt = ObservableInt(View.GONE)
    override val temperatureWeatherText: ObservableText = ObservableText()
    override val locationWeatherText: ObservableText = ObservableText()

    override fun onViewModelStart() {
        setViewLoading()
        fetchData()
    }

    fun setViewLoading() {
        errorViewVisibility.set(View.GONE)
        weatherViewVisibility.set(View.GONE)
        loadingVisibility.set(View.VISIBLE)
    }

    fun setViewFailed() {
        errorViewVisibility.set(View.VISIBLE)
        weatherViewVisibility.set(View.GONE)
        loadingVisibility.set(View.GONE)
    }

    fun setViewSuccess() {
        errorViewVisibility.set(View.GONE)
        weatherViewVisibility.set(View.VISIBLE)
        loadingVisibility.set(View.GONE)
    }

    fun fetchData() {
        val disposable = weatherUseCase.getForecastWeather(
            WeatherRequest(
                -6.226834,
                106.798097,
                BuildConfig.API_KEY
            )
        ).flatMapCompletable {
            setDataWeather(it)
            Completable.complete()
        }.subscribeOn(appScheduler.processScheduler())
            .observeOn(appScheduler.androidScheduler())
            .subscribe(this::setViewSuccess) { setViewFailed() }
        weatherUseCase.addDisposable(disposable)
    }

    fun setDataWeather(it: Weather) {
        temperatureWeatherText.set(
            R.string.main_format_temp_degree,
            Math.round(it.temperature.celcius).toInt().toString()
        )
        locationWeatherText.set(it.region)
    }

    override fun retryClickedButton() {
        onViewModelStart()
    }

    override fun onViewModelDestroy() {
        weatherUseCase.clearDisposable()
    }
}
