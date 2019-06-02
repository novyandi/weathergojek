package com.android.weathergojek.screen.weather.viewModel

import com.android.weathergojek.R
import com.android.weathergojek.domain.model.Forecast
import com.android.weathergojek.screen.base.ViewModel
import com.android.weathergojek.screen.binding.ObservableText
import com.android.weathergojek.screen.formatDateDay
import javax.inject.Inject

/**
 * Created by Novyandi N. on 02/06/2019.
 * novyandinurahmad@hotmail.com
 */
class ItemWeatherForecastViewModel @Inject constructor() : ViewModel {
    val dayForecastText: ObservableText = ObservableText()
    val temperatureForecastText: ObservableText = ObservableText()

    override fun onViewModelStart() {
    }

    fun setDataForecast(forecast: Forecast) {
        dayForecastText.set(forecast.date.formatDateDay())
        temperatureForecastText.set(R.string.item_format_temp_degree, forecast.temperature.celcius.toInt().toString())
    }

    override fun onViewModelDestroy() {
    }
}
