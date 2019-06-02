package com.android.weathergojek.di.module

import com.android.weathergojek.rx.AppScheduler
import com.android.weathergojek.rx.AppSchedulerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
@Module
class ApplicationModule {
    @Provides
    @Singleton
    fun provideAppScheduler(): AppScheduler = AppSchedulerImpl()
}
