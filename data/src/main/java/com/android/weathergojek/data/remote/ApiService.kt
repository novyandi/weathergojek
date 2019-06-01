package com.android.weathergojek.data.remote

import com.android.weathergojek.data.ApiRoutes
import com.android.weathergojek.data.model.WeatherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.QueryMap

/**
 * Created by Novyandi N. on 01/06/2019.
 * novyandinurahmad@hotmail.com
 */
interface ApiService {
    @GET(ApiRoutes.RESOURCE_CURRENT_V1)
    fun getCurrentWeather(@QueryMap parameter: HashMap<String, Any>): Observable<WeatherResponse>

    @GET(ApiRoutes.RESOURCE_FORECAST_V1)
    fun getForecastWeather(@QueryMap parameter: HashMap<String, Any>): Observable<WeatherResponse>
}
