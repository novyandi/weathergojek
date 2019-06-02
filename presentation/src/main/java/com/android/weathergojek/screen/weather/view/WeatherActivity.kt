package com.android.weathergojek.screen.weather.view

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.BottomSheetBehavior
import android.support.v7.widget.LinearLayoutManager
import android.util.DisplayMetrics
import android.view.WindowManager
import com.android.weathergojek.R
import com.android.weathergojek.databinding.ActivityWeatherBinding
import com.android.weathergojek.screen.base.BaseActivity
import com.android.weathergojek.screen.component.RecyclerViewAdapterImpl
import com.android.weathergojek.screen.weather.event.WeatherUIEvent
import com.android.weathergojek.screen.weather.viewModel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_weather.*
import org.greenrobot.eventbus.Subscribe
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

    @Subscribe
    fun adjustBottomSheetPeekHeight(event: WeatherUIEvent.OnShowBottomSheet) {
        //need to execute it in the another thread for UI changes
        Handler().post {
            val topContainerHeight = container_current_weather.measuredHeight
            val peekHeight = getScreenHeight() - topContainerHeight
            val bottomSheetBehaviour = BottomSheetBehavior.from(container_forecast_bottom_sheet)
            bottomSheetBehaviour.peekHeight = peekHeight
            bottomSheetBehaviour.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    private fun getScreenHeight(): Int {
        val wm = getSystemService(Context.WINDOW_SERVICE) as? WindowManager
        return wm?.let {
            val metrics = DisplayMetrics()
            wm.defaultDisplay.getMetrics(metrics)
            metrics.heightPixels
        } ?: 0
    }
}
