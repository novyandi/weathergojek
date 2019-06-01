package com.android.weathergojek.di.module

import com.android.weathergojek.screen.weather.viewModel.WeatherViewModel
import com.android.weathergojek.screen.weather.viewModel.impl.WeatherViewModelImpl
import dagger.Binds
import dagger.Module

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindWeatherViewModel(viewModel: WeatherViewModelImpl): WeatherViewModel
}
