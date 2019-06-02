package com.android.weathergojek.di.module

import android.app.Activity
import android.app.Application
import android.content.Context
import com.android.weathergojek.App
import com.android.weathergojek.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import org.greenrobot.eventbus.EventBus

/**
 * Created by Novyandi N. on 02/06/2019.
 * novyandinurahmad@hotmail.com
 */
@Module
class TestAppModule(val app: App, val activity: Activity) {
    @Provides
    @PerApplication
    fun provideAppContext(): Context = app

    @Provides
    @PerApplication
    fun provideApplication(): Application = app

    @Provides
    fun provideActivity(): Activity = activity
}
