package com.android.weathergojek.screen.weather.viewModel

import android.databinding.ObservableInt
import com.android.weathergojek.screen.base.ViewModel
import com.android.weathergojek.screen.binding.ObservableText

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
interface WeatherViewModel : ViewModel {
    val loadingVisibility: ObservableInt
    val errorViewVisibility: ObservableInt
    val weatherViewVisibility: ObservableInt
    val temperatureWeatherText: ObservableText
    val locationWeatherText: ObservableText
    fun retryClickedButton()
}
