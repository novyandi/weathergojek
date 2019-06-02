package com.android.weathergojek.di.module

import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus

/**
 * Created by Novyandi N. on 02/06/2019.
 * novyandinurahmad@hotmail.com
 */
@Module
class MiscellaneousModule {
    @Provides
    fun provideEventBus(): EventBus = EventBus.getDefault()
}
