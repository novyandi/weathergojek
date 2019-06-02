package com.android.weathergojek.feature.weather

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.NoMatchingViewException
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.view.View
import com.android.weathergojek.BaseUITest
import com.android.weathergojek.R
import com.android.weathergojek.matcher.MatcherEx.recyclerChildMatcher
import com.android.weathergojek.matcher.RecyclerViewInteraction
import com.android.weathergojek.screen.weather.view.WeatherActivity
import com.android.weathergojek.screen.weather.viewModel.ItemWeatherForecastViewModel
import org.junit.Before
import org.junit.Test

/**
 * Created by Novyandi N. on 02/06/2019.
 * novyandinurahmad@hotmail.com
 */
class WeatherPageUITest : BaseUITest<WeatherActivity>(WeatherActivity::class.java) {
    @Before
    override fun setup() {
        super.setup()
        testComponent?.inject(this)
    }

    @Test
    fun checkUI() {
        activityRule.launchActivity(Intent())
        onView(withId(R.id.container_data_weather)).check(matches(matcher.isVisible))
        onView(withId(R.id.container_current_weather)).check(matches(matcher.isVisible))
        onView(withId(R.id.container_current_weather)).check(matches(hasDescendant(withText(getActivity().viewModel.locationWeatherText.get() as String))))
        onView(withId(R.id.container_forecast_bottom_sheet)).check(matches(matcher.isVisible))
        RecyclerViewInteraction.onRecyclerView(withId(R.id.rv_forecast))
            .withRecyclerViewResource(object : RecyclerViewInteraction.RecyclerViewResource {
                override fun withViewModel(index: Int): Any? = getActivity().viewModel.forecastItems[index]
                override fun itemCount(): Int = getActivity().viewModel.forecastItems.size
            }).check(object : RecyclerViewInteraction.ItemViewAssertion {
                override fun check(item: Any, binding: Any, view: View, e: NoMatchingViewException?) {
                    if (item is ItemWeatherForecastViewModel) {
                        matches(recyclerChildMatcher(withId(R.id.item_day_text), matcher.isVisible)).check(view, e)
                        matches(recyclerChildMatcher(withText(item.dayForecastText.get() as String), matcher.isVisible)).check(view, e)
                        matches(recyclerChildMatcher(withId(R.id.temperature_text), matcher.isVisible)).check(view, e)
                    }
                }
            })
        activityRule.finishActivity()
    }
}
