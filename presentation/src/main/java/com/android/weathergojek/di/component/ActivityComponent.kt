package com.android.weathergojek.di.component

import com.android.weathergojek.di.module.MiscellaneousModule
import com.android.weathergojek.di.module.ViewModelModule
import com.android.weathergojek.di.scope.PerActivity
import com.android.weathergojek.screen.weather.view.WeatherActivity
import dagger.Component

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
@PerActivity
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [ViewModelModule::class, MiscellaneousModule::class]
)
interface ActivityComponent {
    fun inject(activity: WeatherActivity)
}
