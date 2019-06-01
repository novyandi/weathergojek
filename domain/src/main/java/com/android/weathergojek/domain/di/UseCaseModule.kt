package com.android.weathergojek.domain.di

import com.android.weathergojek.domain.repository.WeatherRepository
import com.android.weathergojek.domain.usecase.WeatherUseCase
import com.android.weathergojek.domain.usecase.WeatherUseCaseImpl
import dagger.Module
import dagger.Provides

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
@Module
class UseCaseModule {
    @Provides
    fun provideWeatherUseCase(weatherRepository: WeatherRepository): WeatherUseCase {
        return WeatherUseCaseImpl(weatherRepository)
    }
}
