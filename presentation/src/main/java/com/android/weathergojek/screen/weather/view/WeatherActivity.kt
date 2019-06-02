package com.android.weathergojek.screen.weather.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.android.weathergojek.R
import com.android.weathergojek.databinding.ActivityWeatherBinding
import com.android.weathergojek.screen.base.BaseActivity
import com.android.weathergojek.screen.component.RecyclerViewAdapterImpl
import com.android.weathergojek.screen.weather.viewModel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_weather.*
import javax.inject.Inject

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
class WeatherActivity : BaseActivity<ActivityWeatherBinding, WeatherViewModel>() {
    @Inject
    lateinit var adapterForecast: RecyclerViewAdapterImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent?.inject(this)
        bindContentView(R.layout.activity_weather)
        setupForecastList()
    }

    @Suppress("UNCHECKED_CAST")
    private fun setupForecastList() {
        adapterForecast.layoutId = R.layout.item_weather_forecast
        adapterForecast.items = viewModel.forecastItems as MutableList<Any>
        rv_forecast.layoutManager = LinearLayoutManager(this)
        rv_forecast.adapter = adapterForecast
    }
}
