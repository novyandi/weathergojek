package com.android.weathergojek.data.di

import com.android.weathergojek.data.remote.ApiGenerator
import com.android.weathergojek.data.remote.ApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
@Module
class ApiModule(private val baseUrl: String, private val debug: Boolean) {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return ApiGenerator(baseUrl, debug).createApi(ApiService::class.java)
    }
}