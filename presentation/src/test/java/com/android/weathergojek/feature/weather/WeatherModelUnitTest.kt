package com.android.weathergojek.feature.weather

import android.view.View
import com.android.weathergojek.BaseUnitTest
import com.android.weathergojek.R
import com.android.weathergojek.domain.model.Forecast
import com.android.weathergojek.domain.model.Temperature
import com.android.weathergojek.domain.model.Weather
import com.android.weathergojek.domain.usecase.WeatherUseCase
import com.android.weathergojek.rx.AppScheduler
import com.android.weathergojek.screen.base.BaseUIEvent
import com.android.weathergojek.screen.weather.event.WeatherUIEvent
import com.android.weathergojek.screen.weather.viewModel.impl.WeatherViewModelImpl
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.spyk
import io.mockk.verify
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.schedulers.TestScheduler
import org.amshove.kluent.shouldBeEmpty
import org.amshove.kluent.shouldEqual
import org.amshove.kluent.shouldNotBeEmpty
import org.greenrobot.eventbus.EventBus
import org.junit.Test

/**
 * Created by Novyandi N. on 02/06/2019.
 * novyandinurahmad@hotmail.com
 */
class WeatherModelUnitTest : BaseUnitTest() {
    @InjectMockKs
    lateinit var weatherViewModelImpl: WeatherViewModelImpl
    @RelaxedMockK
    lateinit var weatherUseCase: WeatherUseCase
    @RelaxedMockK
    lateinit var eventBus: EventBus
    lateinit var testScheduler: TestScheduler

    override fun setup() {
        super.setup()
        testScheduler = TestScheduler()
        val scheduler = object : AppScheduler {
            override fun processScheduler(): Scheduler {
                return testScheduler
            }

            override fun androidScheduler(): Scheduler {
                return testScheduler
            }
        }
        weatherViewModelImpl = spyk(weatherViewModelImpl).apply { appScheduler = scheduler }
    }

    @Test
    fun checkInitViewModel() {
        weatherViewModelImpl.apply {
            loadingVisibility.get() shouldEqual View.VISIBLE
            errorViewVisibility.get() shouldEqual View.GONE
            weatherViewVisibility.get() shouldEqual View.GONE
            temperatureWeatherText.get() shouldEqual ""
            locationWeatherText.get() shouldEqual ""
            forecastItems.shouldBeEmpty()
        }
    }

    @Test
    fun checkOnViewModelStart() {
        weatherViewModelImpl.onViewModelStart()
        verify(exactly = 1) { weatherViewModelImpl.setViewLoading() }
        verify(exactly = 1) { eventBus.post(any<WeatherUIEvent.OnValidatePermission>()) }
    }

    @Test
    fun checkOnSetViewLoading() {
        weatherViewModelImpl.apply {
            setViewLoading()
            errorViewVisibility.get() shouldEqual View.GONE
            weatherViewVisibility.get() shouldEqual View.GONE
            loadingVisibility.get() shouldEqual View.VISIBLE
        }
    }

    @Test
    fun checkOnSetViewFailed() {
        weatherViewModelImpl.apply {
            setViewFailed()
            errorViewVisibility.get() shouldEqual View.VISIBLE
            weatherViewVisibility.get() shouldEqual View.GONE
            loadingVisibility.get() shouldEqual View.GONE
        }
    }

    @Test
    fun checkOnSetViewSuccess() {
        weatherViewModelImpl.apply {
            setViewSuccess()
            errorViewVisibility.get() shouldEqual View.GONE
            weatherViewVisibility.get() shouldEqual View.VISIBLE
            loadingVisibility.get() shouldEqual View.GONE
        }
        verify(exactly = 1) { eventBus.post(any<WeatherUIEvent.OnShowBottomSheet>()) }
    }


    @Test
    fun checkFetchData_success() {
        every { weatherUseCase.getForecastWeather(any()) } returns Observable.just(
            Weather(
                "jakarta",
                "jakarta",
                "indonesia",
                Temperature(10.0, 10.0),
                listOf(Forecast("date", Temperature(12.0, 21.0)))
            )
        )
        weatherViewModelImpl.fetchData()
        testScheduler.triggerActions()
        verify(exactly = 1) { weatherViewModelImpl.setDataWeather(any()) }
        verify(exactly = 1) { weatherViewModelImpl.setViewSuccess() }
        verify(inverse = true) { weatherViewModelImpl.setViewFailed() }
        verify(exactly = 1) { weatherUseCase.addDisposable(any()) }
    }

    @Test
    fun checkFetchData_failed() {
        every { weatherUseCase.getForecastWeather(any()) } returns Observable.error(Exception())
        weatherViewModelImpl.fetchData()
        testScheduler.triggerActions()
        verify(inverse = true) { weatherViewModelImpl.setDataWeather(any()) }
        verify(inverse = true) { weatherViewModelImpl.setViewSuccess() }
        verify(exactly = 1) { weatherViewModelImpl.setViewFailed() }
        verify(exactly = 1) { weatherUseCase.addDisposable(any()) }
    }

    @Test
    fun checkSetDataWeather() {
        weatherViewModelImpl.apply {
            val mockTemperature = Temperature(.100, .0)
            val region = "region"
            val mockWeather = Weather("", region, "", mockTemperature)
            setDataWeather(mockWeather)
            temperatureWeatherText.get() shouldEqual R.string.main_format_temp_degree
            locationWeatherText.get() shouldEqual region
        }
        verify(exactly = 1) { weatherViewModelImpl.setDataForecast(any()) }
    }

    @Test
    fun checkSetDataForecast() {
        weatherViewModelImpl.apply {
            var forecastList: List<Forecast> = listOf()
            setDataForecast(forecastList)
            forecastItems.shouldBeEmpty()

            val mockTemperature = Temperature(.100, .0)
            forecastList = listOf(Forecast("2019-06-03", mockTemperature))
            setDataForecast(forecastList)
            forecastItems.shouldBeEmpty()

            forecastList = listOf(Forecast("2019-06-03", mockTemperature), Forecast("2019-06-04", mockTemperature))
            setDataForecast(forecastList)
            forecastItems.shouldNotBeEmpty()
            forecastItems.size shouldEqual 1
        }
    }

    @Test
    fun checkRetryClickedButton() {
        weatherViewModelImpl.retryClickedButton()
        verify(exactly = 1) { weatherViewModelImpl.onViewModelStart() }
    }

    @Test
    fun checkOnViewModelDestroy() {
        weatherViewModelImpl.onViewModelDestroy()
        verify(exactly = 1) { weatherUseCase.clearDisposable() }
    }

    @Test
    fun checkOnPermissionDenied() {
        weatherViewModelImpl.onPermissionDenied()
        verify(exactly = 1) { weatherViewModelImpl.setViewFailed() }
        verify(exactly = 1) { eventBus.post(any<BaseUIEvent.OnShowToast>()) }
    }

    @Test
    fun checkOnLocationChanged() {
        weatherViewModelImpl.onLocationChanged(.10, .10)
        verify(exactly = 1) { weatherViewModelImpl.fetchData() }
    }

    @Test
    fun checkOnGetMyCurrentLocationFailed() {
        weatherViewModelImpl.onGetMyCurrentLocationFailed()
        verify(exactly = 1) { weatherViewModelImpl.setViewFailed() }
        verify(exactly = 1) { eventBus.post(any<BaseUIEvent.OnShowToast>()) }
    }
}
