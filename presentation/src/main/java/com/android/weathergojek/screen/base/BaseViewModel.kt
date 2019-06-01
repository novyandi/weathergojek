package com.android.weathergojek.screen.base

import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
abstract class BaseViewModel<T : ViewModel> : ViewModel {

    @Inject
    lateinit var eventBus: EventBus

    fun postEvent(event: Any) = eventBus.post(event)

    override fun onViewStart() {

    }

    override fun onViewDestroy() {

    }
}
