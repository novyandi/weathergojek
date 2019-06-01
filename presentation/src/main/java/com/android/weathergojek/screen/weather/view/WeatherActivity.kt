package com.android.weathergojek.screen.weather.view

import android.os.Bundle
import com.android.weathergojek.R
import com.android.weathergojek.databinding.ActivityWeatherBinding
import com.android.weathergojek.screen.base.BaseActivity
import com.android.weathergojek.screen.weather.viewModel.WeatherViewModel

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
class WeatherActivity : BaseActivity<ActivityWeatherBinding, WeatherViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent?.inject(this)
        bindContentView(R.layout.activity_weather)
    }
}
