package com.android.weathergojek.di.component

import com.android.weathergojek.di.module.MiscellaneousModule
import com.android.weathergojek.di.module.TestAppModule
import com.android.weathergojek.di.module.ViewModelModule
import com.android.weathergojek.di.scope.PerApplication
import com.android.weathergojek.feature.weather.WeatherPageUITest
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Novyandi N. on 02/06/2019.
 * novyandinurahmad@hotmail.com
 */
@PerApplication
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [TestAppModule::class, MiscellaneousModule::class, ViewModelModule::class]
)
interface TestAppComponent : ActivityComponent {
    fun inject(test: WeatherPageUITest)
}
