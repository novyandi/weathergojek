package com.android.weathergojek.di.component

import com.android.weathergojek.data.di.ApiModule
import com.android.weathergojek.data.di.DataSourceModule
import com.android.weathergojek.data.di.RepositoryModule
import com.android.weathergojek.di.module.ApplicationModule
import com.android.weathergojek.di.module.ViewModelModule
import com.android.weathergojek.domain.di.UseCaseModule
import com.android.weathergojek.domain.usecase.WeatherUseCase
import com.android.weathergojek.rx.AppScheduler
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ApiModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
interface ApplicationComponent {
    fun weatherUseCase(): WeatherUseCase
    fun appScheduler(): AppScheduler
}
