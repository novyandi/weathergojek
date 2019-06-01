package com.android.weathergojek.data.di

import com.android.weathergojek.data.Constant
import com.android.weathergojek.data.datasource.WeatherDataSource
import com.android.weathergojek.data.datasource.WeatherLocalDataSource
import com.android.weathergojek.data.datasource.WeatherRemoteDataSource
import com.android.weathergojek.data.remote.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
@Module
class DataSourceModule {

    @Provides
    @Named(Constant.REMOTE_DATASOURCE)
    @Singleton
    fun provideWeatherRemoteDataSource(apiService: ApiService): WeatherDataSource = WeatherRemoteDataSource(apiService)

    @Provides
    @Named(Constant.LOCAL_DATASOURCE)
    @Singleton
    fun provideWeatherLocalDataSource(): WeatherDataSource = WeatherLocalDataSource()
}