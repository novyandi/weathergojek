package com.android.weathergojek.screen.weather.event

sealed class WeatherUIEvent {
    data class OnShowBottomSheet(val tag: String = OnShowBottomSheet::class.java.simpleName)
}
