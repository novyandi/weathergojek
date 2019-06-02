package com.android.weathergojek.screen.base

import com.android.weathergojek.rx.AppScheduler
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
abstract class BaseViewModel<T : ViewModel> : ViewModel {
    @Inject
    lateinit var appScheduler: AppScheduler
    @Inject
    lateinit var eventBus: EventBus

    fun postEvent(event: Any) = eventBus.post(event)
}
