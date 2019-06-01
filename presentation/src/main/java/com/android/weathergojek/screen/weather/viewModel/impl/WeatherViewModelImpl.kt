package com.android.weathergojek.screen.weather.viewModel.impl

import android.databinding.ObservableInt
import android.view.View
import com.android.weathergojek.screen.base.BaseViewModel
import com.android.weathergojek.screen.weather.viewModel.WeatherViewModel
import javax.inject.Inject

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
class WeatherViewModelImpl @Inject constructor() : BaseViewModel<WeatherViewModelImpl>(), WeatherViewModel {
    override val loadingVisibility: ObservableInt = ObservableInt(View.VISIBLE)
}
