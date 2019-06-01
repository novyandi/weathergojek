package com.android.weathergojek.screen.base

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
sealed class BaseUIEvent {
    data class OnShowToast(val message: String, val messageResource: Int)
}