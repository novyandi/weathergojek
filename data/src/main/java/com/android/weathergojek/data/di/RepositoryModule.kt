package com.android.weathergojek.data.di

import com.android.weathergojek.data.Constant
import com.android.weathergojek.data.datasource.WeatherDataSource
import com.android.weathergojek.data.repository.WeatherRepositoryImpl
import com.android.weathergojek.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
@Module
class RepositoryModule {

    @Provides
    fun provideAccountRepository(
        @Named(Constant.LOCAL_DATASOURCE) localDataSource: WeatherDataSource,
        @Named(Constant.REMOTE_DATASOURCE) remoteDataSource: WeatherDataSource
    ): WeatherRepository = WeatherRepositoryImpl(localDataSource, remoteDataSource)
}
