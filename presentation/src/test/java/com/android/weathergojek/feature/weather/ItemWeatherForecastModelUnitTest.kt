package com.android.weathergojek.feature.weather

import com.android.weathergojek.BaseUnitTest
import com.android.weathergojek.R
import com.android.weathergojek.domain.model.Forecast
import com.android.weathergojek.domain.model.Temperature
import com.android.weathergojek.screen.weather.viewModel.ItemWeatherForecastViewModel
import io.mockk.impl.annotations.InjectMockKs
import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 * Created by Novyandi N. on 02/06/2019.
 * novyandinurahmad@hotmail.com
 */
class ItemWeatherForecastModelUnitTest : BaseUnitTest() {
    @InjectMockKs
    lateinit var itemWeatherForecastViewModel: ItemWeatherForecastViewModel

    @Test
    fun checkInitViewModel() {
        itemWeatherForecastViewModel.apply {
            dayForecastText.get() shouldEqual ""
            temperatureForecastText.get() shouldEqual ""
        }
    }

    @Test
    fun checkOnViewModelStart() {

    }

    @Test
    fun checkSetDataForecast() {
        itemWeatherForecastViewModel.apply {
            val forecast = Forecast("2019-06-02", Temperature(.10, .10))
            setDataForecast(forecast)
            dayForecastText.get() shouldEqual "Sunday"
            temperatureForecastText.get() shouldEqual R.string.item_format_temp_degree
        }
    }

    @Test
    fun checkOnViewModelDestroy() {

    }
}
